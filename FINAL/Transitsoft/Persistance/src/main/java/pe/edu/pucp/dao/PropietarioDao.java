package pe.edu.pucp.dao;

import java.util.ArrayList;
import pe.edu.pucp.model.PropietarioDto;

public interface PropietarioDao {

    public Integer insertar(PropietarioDto propietario);

    public PropietarioDto obtenerPorId(Integer propietarioId);

    public ArrayList<PropietarioDto> listarTodos();

    public Integer modificar(PropietarioDto propietario);

    public Integer eliminar(PropietarioDto propietario);
}
