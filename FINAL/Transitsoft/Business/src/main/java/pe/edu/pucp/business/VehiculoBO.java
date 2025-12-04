package pe.edu.pucp.business;

import java.util.ArrayList;
import pe.edu.pucp.daoImp.VehiculoDaoImpl;
import pe.edu.pucp.dao.VehiculoDao;
import pe.edu.pucp.model.VehiculoDto;


public class VehiculoBO {

    private VehiculoDao vehiculoDao;
    
    public VehiculoBO(int dbIndex){
        this.vehiculoDao = new VehiculoDaoImpl(dbIndex);
    }
    public VehiculoBO(){
        this.vehiculoDao = new VehiculoDaoImpl();
    }
    
    public ArrayList<VehiculoDto> listarPorPropietario(String descripionPropietario){
        return this.vehiculoDao.listarPorPropietario(descripionPropietario);
    }
    
    public Integer insertar(VehiculoDto vehiculoDto){
        Integer id = this.vehiculoDao.insertar(vehiculoDto); 
        vehiculoDto.setId(id);
        return id;
    }
    
    public VehiculoDto obtenerPorId(Integer vehiculoId){
        VehiculoDto vehiculoDto = new VehiculoDto();
        vehiculoDto.setId(vehiculoId);
        return this.vehiculoDao.obtenerPorId(vehiculoId);
    }
    
    public ArrayList<VehiculoDto> listarTodos(){
        return this.vehiculoDao.listarTodos();
    }
    
    public Integer modificar(VehiculoDto vehiculo){
        return this.vehiculoDao.modificar(vehiculo);
    }
    
    public Integer eliminar(Integer vehiculoId){
        VehiculoDto vehiculoDto = new VehiculoDto();
        vehiculoDto.setId(vehiculoId);
        return this.vehiculoDao.eliminar(vehiculoDto);
    }
}
