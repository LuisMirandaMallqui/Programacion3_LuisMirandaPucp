package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Captura;

public interface CapturaBO {
    List<Captura> obtenerCapturasConExcesoDeVelocidad();
    void actualizar(Captura modelo);
}
