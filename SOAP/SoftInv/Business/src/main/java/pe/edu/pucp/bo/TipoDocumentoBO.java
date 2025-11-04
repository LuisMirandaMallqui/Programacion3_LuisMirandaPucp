package pe.edu.pucp.bo;

import pe.edu.pucp.dao.TipoDocumentoDAO;
import pe.edu.pucp.daoImp.TipoDocumentoDAOImpl;
import pe.edu.pucp.model.TiposDocumentosDTO;

public class TipoDocumentoBO {
    
    private TipoDocumentoDAO tipoDocumentoDAO;
    
    public TipoDocumentoBO(){
        this.tipoDocumentoDAO = new TipoDocumentoDAOImpl();
    }
    
    public Integer insertar(Integer tipoDocumentoId, String nombre){
        TiposDocumentosDTO tipoDocumentoDTO = new TiposDocumentosDTO();
        tipoDocumentoDTO.setTipoDocumentoId(tipoDocumentoId);
        tipoDocumentoDTO.setNombre(nombre);
        return this.tipoDocumentoDAO.insertar(tipoDocumentoDTO);
    }
    
    public Integer eliminar(Integer tipoDocumentoId){
        TiposDocumentosDTO tipoDocumentoDTO = new TiposDocumentosDTO();
        tipoDocumentoDTO.setTipoDocumentoId(tipoDocumentoId);        
        return this.tipoDocumentoDAO.eliminar(tipoDocumentoDTO);
    }
}
