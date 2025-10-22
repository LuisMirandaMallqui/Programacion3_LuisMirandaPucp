using DataAccess.Domain;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TransitSoftBusiness.BO
{
    public interface IVehiculoBO
    {
        BindingList<Vehiculo> listarVehiculosPorIDPropietario(int idPropietario);
        BindingList<Vehiculo> listarVehiculosSinPropietarioAsignado();
    }
}
