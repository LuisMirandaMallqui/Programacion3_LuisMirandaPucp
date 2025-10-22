using System.Collections.Generic;
using PUCP.TransitSoft.Modelo;

namespace PUCP.TransitSoft.Negocio.BO {
    public interface IInfraccionBO {
        List<Infraccion> Listar();
        int Crear(Infraccion modelo);
    }
}
