package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import java.util.stream.Collectors;
import pe.edu.pucp.transitsoft.bo.mappers.CapturaToInfraccionMapper;
import pe.edu.pucp.transitsoft.dto.Infraccion;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class InfraccionBOImpl implements InfraccionBO {
    @Override
    public List<Infraccion> crearInfracciones(List<Captura> capturasConExceso) {
        var mapper = new CapturaToInfraccionMapper();
        return capturasConExceso.stream()
            .map(mapper::map)
            .collect(Collectors.toList());
    }
}
