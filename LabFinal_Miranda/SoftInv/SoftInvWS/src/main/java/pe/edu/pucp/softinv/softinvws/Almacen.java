/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softinv.softinvws;

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
import pe.edu.pucp.softinv.bo.AlmacenBO;
import pe.edu.pucp.softinv.model.AlmacenesDTO;

/**
 *
 * @author alulab14
 */
@Path("Almacen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Almacen {

    private AlmacenBO almacenBO;

    public Almacen() {
        this.almacenBO = new AlmacenBO();
    }

    // Acá se usan clientes para restful
    @POST
    public Response insertar(AlmacenesDTO almacenesDTO) {
        Integer respuesta = this.almacenBO.insertar(almacenesDTO);
        if (respuesta == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.CREATED).entity(almacenesDTO).build();
    }

    @PUT
    public Response modificar(AlmacenesDTO almacenesDTO) {
        Integer respuesta = this.almacenBO.modificar(almacenesDTO);
        if (respuesta == 0) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.ok(almacenesDTO).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") Integer almacenId) {
        Integer respuesta = this.almacenBO.eliminar(almacenId);
        if (respuesta > 0) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public ArrayList<AlmacenesDTO> listarTodos() {
        return this.almacenBO.listarTodos();
    }

    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") Integer almacenId) {
        AlmacenesDTO almacen = this.almacenBO.obtenerPorId(almacenId);
        if (almacen == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(almacen).build();
    }
}
