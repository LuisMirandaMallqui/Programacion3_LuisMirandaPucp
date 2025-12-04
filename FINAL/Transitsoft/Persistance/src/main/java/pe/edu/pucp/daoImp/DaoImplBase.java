package pe.edu.pucp.daoImp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import pe.edu.pucp.daoImp.util.Columna;
import pe.edu.pucp.daoImp.util.Tipo_Operacion;
import pe.edu.pucp.db.DBManager;

public abstract class DaoImplBase {

    protected String nombreTabla;
    protected ArrayList<Columna> listaColumnas;
    protected Boolean retornarLlavePrimaria;
    protected Connection conexion;
    protected CallableStatement statement;
    protected ResultSet resultSet;
    protected int dbIndex; // Esto es para decidir nosotros que bd usara la persistencia, es lo unico que encontre en mi 
                           //investigación que puede evitar el acoplamiento fuerte, al crear cada DAO especificamos el indice

    public DaoImplBase(String nombre_tabla, int dbIndex) {
        this.nombreTabla = nombre_tabla;
        this.dbIndex = dbIndex; // Se guarda la elección (1 o 2 para el caso de tener solo dos bases de datos)
        this.retornarLlavePrimaria = false;
        this.incluirListaDeColumnas();
    }

    private void incluirListaDeColumnas() {
        this.listaColumnas = new ArrayList<>();
        this.configurarListaDeColumnas();
    }

    protected abstract void configurarListaDeColumnas();

    protected void abrirConexion() {
        this.abrirConexion(dbIndex);
    }

    protected void abrirConexion(int dbIndex) {
        if (this.dbIndex == 2) {
            this.conexion = DBManager.getInstanceBD2().getConnection();
        } else {
            this.conexion = DBManager.getInstanceBD1().getConnection();
        }
    }

    protected void cerrarConexion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.close();
        }
    }

    protected void iniciarTransaccion() throws SQLException {
        this.abrirConexion();
        this.conexion.setAutoCommit(false);
    }

    protected void comitarTransaccion() throws SQLException {
        this.conexion.commit();
    }

    protected void rollbackTransaccion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.rollback();
        }
    }

    protected void colocarSQLEnStatement(String sql) throws SQLException {
        System.out.println(sql);
        this.statement = this.conexion.prepareCall(sql);
    }

    protected Integer ejecutarDMLEnBD() throws SQLException {
        return this.statement.executeUpdate();
    }

    protected void ejecutarSelectEnDB() throws SQLException {
        this.resultSet = this.statement.executeQuery();
    }

    protected Integer insertar() {
        return this.ejecuta_DML(Tipo_Operacion.INSERTAR);
    }

    protected Integer modificar() {
        return this.ejecuta_DML(Tipo_Operacion.MODIFICAR);
    }

    protected Integer eliminar() {
        return this.ejecuta_DML(Tipo_Operacion.ELIMINAR);
    }

    private Integer ejecuta_DML(Tipo_Operacion tipo_operacion) {
        Integer resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = null;
            switch (tipo_operacion) {
                case Tipo_Operacion.INSERTAR:
                    sql = this.generarSQLParaInsercion();
                    break;
                case Tipo_Operacion.MODIFICAR:
                    sql = this.generarSQLParaModificacion();
                    break;
                case Tipo_Operacion.ELIMINAR:
                    sql = this.generarSQLParaEliminacion();
                    break;
            }
            this.colocarSQLEnStatement(sql);
            switch (tipo_operacion) {
                case Tipo_Operacion.INSERTAR:
                    this.incluirValorDeParametrosParaInsercion();
                    break;
                case Tipo_Operacion.MODIFICAR:
                    this.incluirValorDeParametrosParaModificacion();
                    break;
                case Tipo_Operacion.ELIMINAR:
                    this.incluirValorDeParametrosParaEliminacion();
                    break;
            }
            resultado = this.ejecutarDMLEnBD();
            if (this.retornarLlavePrimaria && tipo_operacion == Tipo_Operacion.INSERTAR) {
                resultado = this.retornarUltimoAutoGenerado();
            }
            this.comitarTransaccion();
        } catch (SQLException ex) {
            System.err.println("Error al intentar insertar - " + ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }

    protected String generarSQLParaInsercion() {
        String sql = "INSERT INTO ";
        sql = sql.concat(this.nombreTabla);
        sql = sql.concat("(");
        String sql_columnas = "";
        String sql_parametros = "";
        for (Columna columna : this.listaColumnas) {
            if (!columna.getEsAutoGenerado()) {
                if (!sql_columnas.isBlank()) {
                    sql_columnas = sql_columnas.concat(", ");
                    sql_parametros = sql_parametros.concat(", ");
                }
                sql_columnas = sql_columnas.concat(columna.getNombre());
                sql_parametros = sql_parametros.concat("?");
            }
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(") VALUES (");
        sql = sql.concat(sql_parametros);
        sql = sql.concat(")");
        return sql;
    }

    protected String generarSQLParaModificacion() {
        String sql = "UPDATE ";
        sql = sql.concat(this.nombreTabla);
        sql = sql.concat(" SET ");
        String sql_columnas = "";
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsLlavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    //no está probado
                    sql_predicado = sql_predicado.concat(" AND ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            } else {
                if (!sql_columnas.isBlank()) {
                    sql_columnas = sql_columnas.concat(", ");
                }
                sql_columnas = sql_columnas.concat(columna.getNombre());
                sql_columnas = sql_columnas.concat("=?");
            }
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" WHERE ");
        sql = sql.concat(sql_predicado);
        return sql;
    }

    protected String generarSQLParaEliminacion() {
        String sql = "DELETE FROM ";
        sql = sql.concat(this.nombreTabla);
        sql = sql.concat(" WHERE ");
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsLlavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(", ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            }
        }
        sql = sql.concat(sql_predicado);
        return sql;
    }

    protected String generarSQLParaObtenerPorId() {
        String sql = "SELECT ";
        String sql_columnas = "";
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsLlavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(", ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            }
            if (!sql_columnas.isBlank()) {
                sql_columnas = sql_columnas.concat(", ");
            }
            sql_columnas = sql_columnas.concat(columna.getNombre());
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" FROM ");
        sql = sql.concat(this.nombreTabla);
        sql = sql.concat(" WHERE ");
        sql = sql.concat(sql_predicado);
        return sql;
    }

    protected String generarSQLParaListarTodos() {
        String sql = "SELECT ";
        String sql_columnas = "";
        for (Columna columna : this.listaColumnas) {
            if (!sql_columnas.isBlank()) {
                sql_columnas = sql_columnas.concat(", ");
            }
            sql_columnas = sql_columnas.concat(columna.getNombre());
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" FROM ");
        sql = sql.concat(this.nombreTabla);
        return sql;
    }

    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer retornarUltimoAutoGenerado() {
        Integer resultado = null;
        try {
            String sql = "";
            if (this.dbIndex == 2) {
                sql = DBManager.getInstanceBD2().retornarSQLParaUltimoAutoGenerado();
            } else {
                sql = DBManager.getInstanceBD1().retornarSQLParaUltimoAutoGenerado();
            }
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                resultado = this.resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar retornarUltimoAutoGenerado - " + ex);
        }
        return resultado;
    }

    public void obtenerPorId() {
        try {
            this.abrirConexion();
            String sql = this.generarSQLParaObtenerPorId();
            this.colocarSQLEnStatement(sql);
            this.incluirValorDeParametrosParaObtenerPorId();
            this.ejecutarSelectEnDB();
            if (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
            } else {
                this.limpiarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
    }

    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        throw new UnsupportedOperationException("El método no ha sido sobreescrito."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected void instanciarObjetoDelResultSet() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List listarTodos() {
        String sql = null;
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return this.listarTodos(this.dbIndex, sql, incluirValorDeParametros, parametros);
    }

    public List listarTodos(String sql, Consumer incluirValorDeParametros, Object parametros) {
        return this.listarTodos(this.dbIndex, sql, incluirValorDeParametros, parametros);
    }

    public List listarTodos(int dbIndex, String sql, Consumer incluirValorDeParametros, Object parametros) {
        List lista = new ArrayList<>();
        try {
            this.abrirConexion(dbIndex);
            if (sql == null) {
                sql = this.generarSQLParaListarTodos();
            }
            this.colocarSQLEnStatement(sql);
            if (incluirValorDeParametros != null) {
                incluirValorDeParametros.accept(parametros);
            }
            this.ejecutarSelectEnDB();
            while (this.resultSet.next()) {
                agregarObjetoALaLista(lista);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return lista;
    }

    protected void agregarObjetoALaLista(List lista) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void ejecutarProcedimientoAlmacenado(String sql, Boolean conTransaccion) {
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        this.ejecutarProcedimientoAlmacenado(sql, incluirValorDeParametros, parametros, conTransaccion);
    }

    public void ejecutarProcedimientoAlmacenado(String sql, Consumer incluirValorDeParametros, Object parametros, Boolean conTransaccion) {
        try {
            if (conTransaccion) {
                this.iniciarTransaccion();
            }
            this.colocarSQLEnStatement(sql);
            if (incluirValorDeParametros != null) {
                incluirValorDeParametros.accept(parametros);
            }
            this.ejecutarDMLEnBD();
            if (conTransaccion) {
                this.comitarTransaccion();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar ejecutar procedimiento almacenado: " + ex);
            try {
                if (conTransaccion) {
                    this.rollbackTransaccion();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
    }
}
