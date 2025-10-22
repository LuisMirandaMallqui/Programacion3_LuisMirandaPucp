package pe.edu.pucp.transitsoft.daoimpl;

import java.sql.SQLException;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.daoimpl.util.TipoOperacion;
import pe.edu.pucp.transitsoft.modelo.Camara;
import pe.edu.pucp.transitsoft.modelo.Captura;
import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;
import pe.edu.pucp.transitsoft.modelo.Propietario;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

// TODO: Implementar CapturaDAOImpl
public class CapturaDAOImpl extends DAOImplBase implements CapturaDAO {

    private Captura captura;
    
    public CapturaDAOImpl()
    {
        this.captura = null;
    }
    @Override
    public List<Captura> leerTodos() {
        return super.leerTodos();
    }

    @Override
    public boolean actualizar(Captura captura2) {
        captura = captura2;
        return super.ejecutarCRUD(TipoOperacion.ACTUALIZAR);
    }
    
    @Override
    protected String obtenerProcemiendoAlmacenadoListar(){
        String sql = "{ call listarCapturas() }";
        return sql;
    }
    
    @Override
    protected String obtenerProcedimiendoParaModificacion(){
        String sql = "{ call modificarEstadoCaptura(?,?) }";
        return sql;
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() {
        try {
            this.statement.setString(1,"PROCESADO");
            this.statement.setInt(2, captura.getId());
            System.out.println(captura.getId());
        } catch (SQLException ex) {
            System.err.println("Error al incluir parámetros para modificación - " + ex);
        }
    }
    
    @Override
    protected void instanciarObjetoDelResultSetListar() throws SQLException {
         this.captura = new Captura();
         Camara camara = new Camara();
         Vehiculo vehiculo = new Vehiculo();
         Propietario propietario = new Propietario();
         this.captura.setId(this.resultSet.getInt("c.id"));
         this.captura.setPlaca(this.resultSet.getString("c.placa"));
         this.captura.setVelocidad(this.resultSet.getDouble("c.velocidad"));
         this.captura.setEstado("REGISTRADO".equals(this.resultSet.getString("c.estado")) ? EstadoCaptura.REGISTRADO : EstadoCaptura.PROCESADO);
         
         camara.setId(this.resultSet.getInt("c.id_camara"));
         camara.setModelo(this.resultSet.getString("camara_modelo"));
         camara.setCodigoSerie(this.resultSet.getString("camara_codigo_serie"));
         camara.setLatitud(this.resultSet.getInt("camara_latitud"));
         camara.setLongitud(this.resultSet.getInt("camara_longitud"));
         propietario.setId(this.resultSet.getInt("id_propietario"));
         
         propietario.setDni(this.resultSet.getString("propietario_dni"));
         propietario.setNombres(this.resultSet.getString("propietario_nombres"));
         propietario.setApellidos(this.resultSet.getString("propietario_apellidos"));
         propietario.setDireccion(this.resultSet.getString("propietario_direccion"));
         
         vehiculo.setId(this.resultSet.getInt("id_vehiculo"));
         vehiculo.setPlaca(this.resultSet.getString("vehiculo_placa"));
         vehiculo.setMarca(this.resultSet.getString("vehiculo_marca"));
         vehiculo.setModelo(this.resultSet.getString("vehiculo_modelo"));
         vehiculo.setAnho(this.resultSet.getInt("vehiculo_anho"));
         vehiculo.setPropietario(propietario);
         
         this.captura.setCamara(camara);
         this.captura.setVehiculo(vehiculo);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet(){
        this.captura = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.limpiarObjetoDelResultSet();
        this.instanciarObjetoDelResultSetListar();
        lista.add(this.captura);
    }
    
}