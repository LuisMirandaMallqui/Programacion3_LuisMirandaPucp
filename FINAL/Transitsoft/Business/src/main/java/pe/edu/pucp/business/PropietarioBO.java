package pe.edu.pucp.business;

import java.util.ArrayList;
import pe.edu.pucp.dao.PropietarioDao;
import pe.edu.pucp.daoImp.PropietarioDaoImpl;
import pe.edu.pucp.model.PropietarioDto;

public class PropietarioBO {

    private PropietarioDao propietarioDao;

    public PropietarioBO() {
        this.propietarioDao = new PropietarioDaoImpl();
    }

    public Integer insertar(PropietarioDto propietarioDto) {
        Integer id = this.propietarioDao.insertar(propietarioDto);
        propietarioDto.setId(id);
        return id;
    }

    public PropietarioDto obtenerPorId(Integer propietarioId) {
        PropietarioDto propietarioDto = new PropietarioDto();
        propietarioDto.setId(propietarioId);
        return this.propietarioDao.obtenerPorId(propietarioId);
    }

    public ArrayList<PropietarioDto> listarTodos() {
        return this.propietarioDao.listarTodos();
    }

    public Integer modificar(PropietarioDto propietario) {
        return this.propietarioDao.modificar(propietario);
    }

    public Integer eliminar(Integer propietarioId) {
        PropietarioDto propietarioDto = new PropietarioDto();
        propietarioDto.setId(propietarioId);
        return this.propietarioDao.eliminar(propietarioDto);
    }
}
