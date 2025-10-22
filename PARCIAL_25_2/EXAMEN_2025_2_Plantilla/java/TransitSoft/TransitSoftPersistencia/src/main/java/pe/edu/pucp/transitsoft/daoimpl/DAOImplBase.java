package pe.edu.pucp.transitsoft.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.daoimpl.util.TipoOperacion;
import pe.edu.pucp.transitsoft.db.DBManager;
import pe.edu.pucp.transitsoft.modelo.Captura;


public abstract class DAOImplBase{
    
    protected Connection conexion;
    protected CallableStatement statement;
    protected ResultSet resultSet;
    
    protected void abrirConexion() throws SQLException {
        this.conexion = DBManager.getInstance().getConnection();
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
        this.statement = this.conexion.prepareCall(sql);
    }
    
    protected void ejecutarSelectEnBD() throws SQLException {
        this.resultSet = this.statement.executeQuery();
    }
    
    protected int ejecutarDMLEnBD() throws SQLException {
        return this.statement.executeUpdate();
    }
    
    public boolean ejecutarCRUD(TipoOperacion tipo_operacion){
        boolean resultado = false;
        
        try {
            this.iniciarTransaccion();
            String sql = null;
            switch (tipo_operacion) {
                case INSERTAR:
                    //sql = this.generarSQLParaInsercion();
                    break;
                case ACTUALIZAR:
                    sql = this.obtenerProcedimiendoParaModificacion();
                    break;
                case ELIMINAR:
                    //sql = this.generarSQLParaEliminacion();
                    break;
            }
            this.colocarSQLEnStatement(sql);
            switch (tipo_operacion) {
                case INSERTAR:
                    //this.incluirValorDeParametrosParaInsercion();
                    break;
                case ACTUALIZAR:
                    this.incluirValorDeParametrosParaModificacion();
                    break;
                case ELIMINAR:
                    //this.incluirValorDeParametrosParaEliminacion();
                    break;
            }
            this.ejecutarDMLEnBD();
            this.comitarTransaccion();
            resultado = true;
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
    
    public List leerTodos(){
        List lista = new ArrayList<>();
        try {
            this.abrirConexion();
            String sql = obtenerProcemiendoAlmacenadoListar();
            colocarSQLEnStatement(sql);
            ejecutarSelectEnBD();
            while (this.resultSet.next()) {
                agregarObjetoALaLista(lista);
            }
        } catch (SQLException e) {
            System.err.println("Error al intentar leerTodos - " + e);
        }finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }

        return lista;
    }
    
    protected String obtenerProcemiendoAlmacenadoListar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    protected String obtenerProcedimiendoParaModificacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    protected void incluirValorDeParametrosParaModificacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    protected void instanciarObjetoDelResultSetListar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
