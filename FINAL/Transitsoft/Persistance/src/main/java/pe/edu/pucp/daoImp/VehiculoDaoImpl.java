package pe.edu.pucp.daoImp;

import pe.edu.pucp.daoImp.DaoImplBase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.daoImp.util.Columna;
import pe.edu.pucp.model.PropietarioDto;
import pe.edu.pucp.model.VehiculoDto;
import pe.edu.pucp.dao.VehiculoDao;

public class VehiculoDaoImpl extends DaoImplBase implements VehiculoDao {

    private VehiculoDto vehiculoDTO;

    public VehiculoDaoImpl(int bdObjetivo) {
        super("VEHICULO", bdObjetivo);
        this.vehiculoDTO = null;
        this.retornarLlavePrimaria = true;
    }

    public VehiculoDaoImpl() {
        this(1); // Por defecto los DAO's se conectan a la bd1
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID", true, true));
        this.listaColumnas.add(new Columna("PLACA", false, false));
        this.listaColumnas.add(new Columna("MARCA", false, false));
        this.listaColumnas.add(new Columna("MODELO", false, false));
        this.listaColumnas.add(new Columna("ANHO", false, false));
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {

        this.vehiculoDTO = new VehiculoDto();
        this.vehiculoDTO.setId(this.resultSet.getInt("ID_VEHICULO"));
        this.vehiculoDTO.setPlaca(this.resultSet.getString("PLACA"));
        this.vehiculoDTO.setMarca(this.resultSet.getString("MARCA"));
        this.vehiculoDTO.setModelo(this.resultSet.getString("MODELO"));
        this.vehiculoDTO.setAnho(this.resultSet.getInt("ANHO"));

        int idPropietario = this.resultSet.getInt("ID_PROPIETARIO");
        if (idPropietario > 0) {
            PropietarioDto propietario = new PropietarioDto();
            propietario.setId(idPropietario);
            propietario.setDni(this.resultSet.getString("DNI"));
            propietario.setNombres(this.resultSet.getString("NOMBRES"));
            propietario.setApellidos(this.resultSet.getString("APELLIDOS"));
            propietario.setDireccion(this.resultSet.getString("DIRECCION"));

            this.vehiculoDTO.setPropietario(propietario);
        } else {
            // Si es 0 ponemos null explícitamente
            this.vehiculoDTO.setPropietario(null);
        }

    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.vehiculoDTO = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.vehiculoDTO);
    }

    @Override
    public ArrayList<VehiculoDto> listarPorPropietario(String descripionPropietario) {
        String sql = this.generarSQLParaListarPorPropietario();
        ArrayList<VehiculoDto> listado = (ArrayList<VehiculoDto>) super.listarTodos(sql, this::incluirValorDeParametrosParaListarPorPropietario, descripionPropietario);
        return listado;
    }

    private String generarSelectBase() {
        String sql = "SELECT V.ID AS ID_VEHICULO, ";
        sql = sql.concat("V.PLACA, V.MARCA, V.MODELO, V.ANHO, ");
        sql = sql.concat("P.ID AS ID_PROPIETARIO, ");
        sql = sql.concat("P.DNI, P.NOMBRES, P.APELLIDOS, P.DIRECCION ");
        sql = sql.concat("FROM VEHICULO V ");
        // Usamos LEFT JOIN para que no falle si el vehículo no tiene propietario asignado 
        // O INNER JOIN si es obligatorio.
        sql = sql.concat("LEFT JOIN VEHICULO_PROPIETARIO VP ON VP.ID_VEHICULO = V.ID ");
        sql = sql.concat("LEFT JOIN PROPIETARIO P ON P.ID = VP.ID_PROPIETARIO ");
        return sql;
    }

    @Override
    protected String generarSQLParaListarTodos() {
        return generarSelectBase(); // Reutilizamos el string base
    }

    @Override
    protected String generarSQLParaObtenerPorId() {
        String sql = generarSelectBase();
        sql = sql.concat(" WHERE V.ID = ?"); // Agregamos el filtro por ID
        return sql;
    }

    private String generarSQLParaListarPorPropietario() {
        String sql = generarSelectBase();
        sql = sql.concat("WHERE (P.DNI LIKE ? OR P.NOMBRES LIKE ? OR P.APELLIDOS LIKE ?); ");
        return sql;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        // "WHERE ID = ?
        this.statement.setInt(1, this.vehiculoDTO.getId());
    }

    private void incluirValorDeParametrosParaListarPorPropietario(Object objetoParametros) {
        String cadena = (String) objetoParametros;
        cadena = cadena.trim();
        cadena = "%".concat(cadena).concat("%");
        try {
            this.statement.setString(1, cadena);
            this.statement.setString(2, cadena);
            this.statement.setString(3, cadena);
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Integer insertar(VehiculoDto vehiculo) {
        this.vehiculoDTO = vehiculo;
        return super.insertar();
    }

    @Override
    public VehiculoDto obtenerPorId(Integer vehiculoId) {
        this.vehiculoDTO = new VehiculoDto();
        this.vehiculoDTO.setId(vehiculoId);
        super.obtenerPorId();
        return this.vehiculoDTO;
    }

    @Override
    public ArrayList<VehiculoDto> listarTodos() {
        return (ArrayList<VehiculoDto>) super.listarTodos();
    }

    @Override
    public Integer modificar(VehiculoDto almacen) {
        this.vehiculoDTO = almacen;
        return super.modificar();
    }

    @Override
    public Integer eliminar(VehiculoDto almacen) {
        this.vehiculoDTO = almacen;
        return super.eliminar();
    }
}
