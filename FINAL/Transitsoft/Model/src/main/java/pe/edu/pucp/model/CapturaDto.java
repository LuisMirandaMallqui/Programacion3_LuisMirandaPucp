package pe.edu.pucp.model;

import java.util.Date;

public class CapturaDto {
    private Integer id;
    private String placa;
    private Double velocidad;
    private Date fechaCaptura;
    private CamaraDto camara;
    private EstadoCapturaDto estado;
    private VehiculoDto vehiculo;

    public CapturaDto() {
        this.id = null;
        this.placa = null;
        this.velocidad = null;
        this.fechaCaptura = null;
        this.camara = null;
        this.estado = null;
        this.vehiculo = null;
    }
    
    public CapturaDto(Integer id, String placa, Double velocidad, Date fechaCaptura, CamaraDto camara, EstadoCapturaDto estado, VehiculoDto vehiculo) {
        this.id = id;
        this.placa = placa;
        this.velocidad = velocidad;
        this.fechaCaptura = fechaCaptura;
        this.camara = camara;
        this.estado = estado;
        this.vehiculo = vehiculo;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the velocidad
     */
    public Double getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad the velocidad to set
     */
    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * @return the fechaCaptura
     */
    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    /**
     * @param fechaCaptura the fechaCaptura to set
     */
    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    /**
     * @return the camara
     */
    public CamaraDto getCamara() {
        return camara;
    }

    /**
     * @param camara the camara to set
     */
    public void setCamara(CamaraDto camara) {
        this.camara = camara;
    }

    /**
     * @return the estado
     */
    public EstadoCapturaDto getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoCapturaDto estado) {
        this.estado = estado;
    }

    /**
     * @return the vehiculo
     */
    public VehiculoDto getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(VehiculoDto vehiculo) {
        this.vehiculo = vehiculo;
    }

    
}