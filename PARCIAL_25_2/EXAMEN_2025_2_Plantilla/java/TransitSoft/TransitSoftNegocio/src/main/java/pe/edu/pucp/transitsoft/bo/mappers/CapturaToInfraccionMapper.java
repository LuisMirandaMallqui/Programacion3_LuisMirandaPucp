package pe.edu.pucp.transitsoft.bo.mappers;

import java.util.Date;
import pe.edu.pucp.transitsoft.dto.Infraccion;
import pe.edu.pucp.transitsoft.modelo.Captura;

/**
 *
 * @author eric
 */
public class CapturaToInfraccionMapper implements Mapper<Captura, Infraccion> {
    @Override
    public Infraccion map(Captura captura) {
        Infraccion infraccion = new Infraccion();
        
        infraccion.setPlaca(captura.getPlaca());
        infraccion.setvelocidad(captura.getVelocidad());
        infraccion.setLimite(50.00);
        infraccion.setExceso(captura.getVelocidad() - 50.00);
        infraccion.setMarcaVehiculo(captura.getVehiculo().getMarca());
        infraccion.setModeloVehiculo(captura.getVehiculo().getModelo());
        infraccion.setAnhoVehiculo(captura.getVehiculo().getAnho());
        infraccion.setDniPropietario(captura.getVehiculo().getPropietario().getDni());
        infraccion.setNombresPropietario(captura.getVehiculo().getPropietario().getNombres());
        infraccion.setApellidosPropietario(captura.getVehiculo().getPropietario().getApellidos());
        infraccion.setDireccionPropietario(captura.getVehiculo().getPropietario().getDireccion());
        infraccion.setModeloCamara(captura.getCamara().getModelo());
        infraccion.setCodigoSerieCamara(captura.getCamara().getCodigoSerie());
        infraccion.setLatitud(captura.getCamara().getLatitud());
        infraccion.setLongitud(captura.getCamara().getLongitud());
        infraccion.setMonto(950.00);
        infraccion.setFechaCaptura(captura.getFechaCaptura());
        infraccion.setFechaRegistro(new Date());
        
        return infraccion;
    }
}
