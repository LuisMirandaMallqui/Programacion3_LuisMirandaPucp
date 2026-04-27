/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.ws;

import com.mysql.cj.xdevapi.ExprUtil;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import pe.edu.pucp.business.PropietarioBO;
import pe.edu.pucp.model.PropietarioDto;

/**
 *
 * @author alulab14
 */
@Path("Propietario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Propietario {

    private PropietarioBO propietarioBO;

    public Propietario() {
        this.propietarioBO = new PropietarioBO();
    }

    // Acá se usan clientes para restful
    @POST
    public Response insertar(PropietarioDto propietarioDto) {
        Integer respuesta = this.propietarioBO.insertar(propietarioDto);
        if (respuesta == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.CREATED).entity(propietarioDto).build();
    }

    @PUT
    public Response modificar(PropietarioDto propietarioDto) {
        Integer respuesta = this.propietarioBO.modificar(propietarioDto);
        if (respuesta == 0) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.ok(propietarioDto).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") Integer propietarioId) {
        Integer respuesta = this.propietarioBO.eliminar(propietarioId);
        if (respuesta > 0) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public ArrayList<PropietarioDto> listarTodos() {
        return this.propietarioBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") Integer propietarioId) {
        PropietarioDto propietario = this.propietarioBO.obtenerPorId(propietarioId);
        if (propietario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(propietario).build();
    }
}
