package pe.edu.pucp.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import pe.edu.pucp.model.VehiculoDto;

public class VehiculoBO {

//    private VehiculoDao vehiculoDao;
    private HttpClient cliente;
    private HttpRequest request;
    private String url;
    private HttpResponse<String> response;
//    public VehiculoBO(int dbIndex) {
//        this.vehiculoDao = new VehiculoDaoImpl(dbIndex);
//    }

    public VehiculoBO(int dbIndex) {

    }

    public VehiculoBO() {
//        this.vehiculoDao = new VehiculoDaoImpl();
        this.url = "http://localhost:8080/WS/resources/Vehiculo";
    }

    public ArrayList<VehiculoDto> listarPorPropietario(String descripionPropietario) {
        return this.vehiculoDao.listarPorPropietario(descripionPropietario);
    }

    public Integer insertar(String placa, String marca, String modelo, Integer anho) throws JsonProcessingException, IOException, InterruptedException {
        //EN ROJO
        this.crearHttpClient();

        // EN BLANCO
        VehiculoDto vehiculoDto = new VehiculoDto();
        vehiculoDto.setPlaca(placa);
        vehiculoDto.setMarca(marca);
        vehiculoDto.setModelo(modelo);
        vehiculoDto.setAnho(anho);
        //EN VERDE - Serialización
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(vehiculoDto);

        //EN AZUL
        this.crearHttpRequestPOST(jsonRequest);

        //EN NEGRO -> envio
        this.enviarRequest();

        //EN VERDE - Deseralización
        VehiculoDto vehiculoRespuesta = mapper.readValue(response.body(), VehiculoDto.class);
        this.cerrarHttpClient();

        if (response.statusCode() == Response.Status.CREATED.getStatusCode()) {
            return vehiculoRespuesta.getId();
        }
        return 0;
    }

    public VehiculoDto obtenerPorId(Integer vehiculoId) {
        VehiculoDto vehiculoDto = new VehiculoDto();
        vehiculoDto.setId(vehiculoId);
        return this.vehiculoDao.obtenerPorId(vehiculoId);
    }

    public ArrayList<VehiculoDto> listarTodos() {
        return this.vehiculoDao.listarTodos();
    }

    public Integer modificar(VehiculoDto vehiculo) {
        return this.vehiculoDao.modificar(vehiculo);
    }

    public Integer eliminar(Integer vehiculoId) throws IOException, InterruptedException {
        this.crearHttpClient();
        this.crearHttpRequestDELETE(vehiculoId);
        this.enviarRequest();
        this.cerrarHttpClient();
        if (this.response.statusCode() == Response.Status.NO_CONTENT.getStatusCode()) {
            return vehiculoId;
        }
        return 0;
    }

    private void crearHttpClient() {
        this.cliente = HttpClient.newHttpClient();
    }

    private void cerrarHttpClient() {
        this.cliente.close();
    }

    private void crearHttpRequestPOST(String jsonRequest) {
        //EN AZUL
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
    }

    private void crearHttpRequestDELETE(Integer vehiculoId) {
        // EN AZUL
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.url + "/" + vehiculoId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
    }

    private void enviarRequest() throws IOException, InterruptedException {
        //EN NEGRO -> envio
        this.response = this.cliente.send(this.request, HttpResponse.BodyHandlers.ofString());
    }
}
