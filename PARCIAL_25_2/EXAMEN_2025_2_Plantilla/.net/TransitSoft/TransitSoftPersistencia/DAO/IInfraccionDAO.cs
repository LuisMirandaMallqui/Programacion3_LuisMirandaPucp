using PUCP.TransitSoft.Modelo;
using System.Collections.Generic;

namespace PUCP.TransitSoft.Persistencia.DAO {
    public interface IInfraccionDAO {
        int Crear(Infraccion modelo);
        List<Infraccion> LeerTodos();
    }
}
