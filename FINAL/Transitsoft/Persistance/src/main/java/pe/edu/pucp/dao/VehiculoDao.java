package pe.edu.pucp.dao;

import java.util.ArrayList;
import pe.edu.pucp.model.VehiculoDto;

public interface VehiculoDao {

    public ArrayList<VehiculoDto> listarPorPropietario(String descripion_propietario);

    public Integer insertar(VehiculoDto vehiculoDto);

    public VehiculoDto obtenerPorId(Integer vehiculoId);

    public ArrayList<VehiculoDto> listarTodos();

    public Integer modificar(VehiculoDto vehiculoDto);

    public Integer eliminar(VehiculoDto vehiculoDto);
}
