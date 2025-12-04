package pe.edu.pucp.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.business.PropietarioBO;
import pe.edu.pucp.model.PropietarioDto;

@WebService(serviceName = "Propietario")
public class PropietarioWS {

    private PropietarioBO propietarioBO;

    public PropietarioWS() {
        this.propietarioBO = new PropietarioBO();
    }
//
//    @WebMethod(operationName = "inserta_propietario")
//    public Integer insertar(@WebParam(name = "nombres")String nombre, @WebParam(name = "apellidos")String apellidos,@WebParam(name = "DNI")String dni,@WebParam(name = "direccion")String direccion){
//        return this.propietarioBO.insertar(nombre, apellidos,dni,direccion);
//    }

    @WebMethod(operationName = "obtenerPorId")
    public PropietarioDto obtenerPorId(@WebParam(name = "id") Integer propietarioId) {
        return this.propietarioBO.obtenerPorId(propietarioId);
    }

    @WebMethod(operationName = "listarTodos")
    public ArrayList<PropietarioDto> listarTodos() {
        return this.propietarioBO.listarTodos();
    }

//    @WebMethod(operationName = "modificar")
//    public Integer modificar(@WebParam(name = "propietarioId") Integer propietarioId, @WebParam(name = "nombres") String nombres, @WebParam(name = "apellidos") String apellidos, @WebParam(name = "dni") String dni, @WebParam(name = "direccion") String direccion) {
//        return this.propietarioBO.modificar(propietarioId, nombres, apellidos, dni, direccion);
//    }
    @WebMethod(operationName = "eliminar")
    public Integer eliminar(@WebParam(name = "id") Integer propietarioId) {
        return this.propietarioBO.eliminar(propietarioId);
    }
}
