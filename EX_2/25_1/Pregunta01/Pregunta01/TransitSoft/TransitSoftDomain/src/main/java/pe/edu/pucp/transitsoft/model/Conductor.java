package pe.edu.pucp.transitsoft.model;

public class Conductor {
    private int idConductor;
    private int idTipoLicencia;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroLicencia;
    private int puntosAcumulados;
    private Vehiculo vehiculo;//Para el POST

    public Conductor() {
    }

    public int getConductorId() {
        return idConductor;
    }

    public void setConductorId(int idConductor) {
        this.idConductor = idConductor;
    }

    public int getIdTipoLicencia() {
        return idTipoLicencia;
    }

    public void setIdTipoLicencia(int idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return apellidoPaterno;
    }

    public void setPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getMaterno() {
        return apellidoMaterno;
    }

    public void setMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNumLicencia() {
        return numeroLicencia;
    }

    public void setNumLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
}
