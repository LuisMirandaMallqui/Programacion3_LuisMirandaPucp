package pe.edu.pucp.transitsoft.dto;

import java.util.Date;

public class Infraccion {
    private String placa;
    private double velocidad;
    private double limite;
    private double exceso;
    private String marcaVehiculo;
    private String modeloVehiculo;
    private int anhoVehiculo;
    private String dniPropietario;
    private String nombresPropietario;
    private String apellidosPropietario;
    private String direccionPropietario;
    private String modeloCamara;
    private String codigoSerieCamara;
    private long latitud;
    private long longitud;
    private double monto;
    private Date fechaCaptura;
    private Date fechaRegistro;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getvelocidad() {
        return velocidad;
    }

    public void setvelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getExceso() {
        return exceso;
    }

    public void setExceso(double exceso) {
        this.exceso = exceso;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public int getAnhoVehiculo() {
        return anhoVehiculo;
    }

    public void setAnhoVehiculo(int anhoVehiculo) {
        this.anhoVehiculo = anhoVehiculo;
    }

    public String getDniPropietario() {
        return dniPropietario;
    }

    public void setDniPropietario(String dniPropietario) {
        this.dniPropietario = dniPropietario;
    }

    public String getNombresPropietario() {
        return nombresPropietario;
    }

    public void setNombresPropietario(String nombresPropietario) {
        this.nombresPropietario = nombresPropietario;
    }

    public String getApellidosPropietario() {
        return apellidosPropietario;
    }

    public void setApellidosPropietario(String apellidosPropietario) {
        this.apellidosPropietario = apellidosPropietario;
    }

    public String getDireccionPropietario() {
        return direccionPropietario;
    }

    public void setDireccionPropietario(String direccionPropietario) {
        this.direccionPropietario = direccionPropietario;
    }

    public String getModeloCamara() {
        return modeloCamara;
    }

    public void setModeloCamara(String modeloCamara) {
        this.modeloCamara = modeloCamara;
    }

    public String getCodigoSerieCamara() {
        return codigoSerieCamara;
    }

    public void setCodigoSerieCamara(String codigoSerieCamara) {
        this.codigoSerieCamara = codigoSerieCamara;
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
