package pe.edu.pucp.dao;

import java.util.ArrayList;
import pe.edu.pucp.model.AlmacenesDTO;

public interface AlmacenDAO {
    
    public Integer insertar(AlmacenesDTO almacen);
    
    public AlmacenesDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<AlmacenesDTO> listarTodos();
    
    public Integer modificar(AlmacenesDTO almacen);
    
    public Integer eliminar(AlmacenesDTO almacen);
}
