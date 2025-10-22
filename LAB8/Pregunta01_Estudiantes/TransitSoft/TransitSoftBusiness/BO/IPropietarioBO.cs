using DataAccess.Domain;
using DataAccess.Persistance;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TransitSoftBusiness.BO
{
    public interface IPropietarioBO
    {
        BindingList<Propietario> listarPropietariosConVehiculosAsignados();
        BindingList<Propietario> listarPropietariosSinVehiculoAsignado();
        int registrarAsignacionDeVehiculosAPropietario(Propietario Propietario);
    }
}
