package pe.edu.pucp.transitsoft.dao;

import java.util.List;
import pe.edu.pucp.transitsoft.model.Conductor;
import pe.edu.pucp.transitsoft.model.Vehiculo;


public interface IConductorDAO {
    List<Conductor> listarTodosLicencia();
    List<Vehiculo> listarTodosVehiculos(int idConductor);
    void insertar(Conductor conductor);
}
