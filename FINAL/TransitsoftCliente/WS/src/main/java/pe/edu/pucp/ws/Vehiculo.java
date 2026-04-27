/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.ws;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import pe.edu.pucp.model.PropietarioDto;
import pe.edu.pucp.model.VehiculoDto;
import pe.edu.pucp.business.VehiculoBO;

/**
 *
 * @author luism
 */
@Path("Vehiculo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Vehiculo {

    private VehiculoBO vehiculoBO;

    public Vehiculo() {
        this.vehiculoBO = new VehiculoBO();
    }

    @GET
    public ArrayList<VehiculoDto> listar(@QueryParam("busqueda") String busqueda) {
        try {
            ArrayList<VehiculoDto> lista;

            // Si mandaron algo en busqueda filtramos
            if (busqueda != null && !busqueda.isBlank()) {
                lista = this.vehiculoBO.listarPorPropietario(busqueda);
            } else {
                // De otra forma, listamos todo
                lista = this.vehiculoBO.listarTodos();
            }

            return lista;
        } catch (Exception ex) {
//            return Response.serverError().entity(ex.getMessage()).build();
            throw new WebApplicationException(
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("Error en el servidor: " + ex.getMessage())
                            .type(MediaType.TEXT_PLAIN)
                            .build()
            );
        }
    }

    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") Integer vehiculoId) {
        VehiculoDto vehiculo = this.vehiculoBO.obtenerPorId(vehiculoId);
        if (vehiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vehiculo).build();
    }

    @POST
    public Response insertar(VehiculoDto vehiculoDto) {
        Integer respuesta = this.vehiculoBO.insertar(vehiculoDto);
        if (respuesta == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.CREATED).entity(vehiculoDto).build();
    }

    @PUT
    public Response modificar(VehiculoDto vehiculoDto) {
        Integer respuesta = this.vehiculoBO.modificar(vehiculoDto);
        if (respuesta == 0) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.ok(vehiculoDto).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(Integer vehiculoId) {
        Integer respuesta = this.vehiculoBO.eliminar(vehiculoId);
        if (respuesta > 0) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
