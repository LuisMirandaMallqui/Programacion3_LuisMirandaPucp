package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.daoimpl.CapturaDAOImpl;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class CapturaBOImpl implements CapturaBO {
    private final CapturaDAO capturaDao;
    
    public CapturaBOImpl() {
        this.capturaDao = new CapturaDAOImpl();
    }
    
    @Override
    public List<Captura> obtenerCapturasConExcesoDeVelocidad() {
        List lista2 = new ArrayList<Captura>();
        Captura captura;
        List lista = this.capturaDao.leerTodos();
        for (Iterator it = lista.iterator(); it.hasNext();) {
            captura = (Captura) it.next();
            if(captura.getVelocidad()>50.00)
                lista2.add(captura);
        }
        return lista2;
    }

    @Override
    public void actualizar(Captura modelo) {
        if(this.capturaDao.actualizar(modelo)){
            System.out.println(modelo.getId());
            System.out.println("actualizó");}
        else
            System.out.println("no actualizado");
    }
}
