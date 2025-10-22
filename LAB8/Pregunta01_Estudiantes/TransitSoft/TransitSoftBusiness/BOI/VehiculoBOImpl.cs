using DataAccess.Domain;
using DataAccess.Persistance;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoftBusiness.BO;
using TransitSoftDBManager;

namespace TransitSoftBusiness.BOI
{
    public class VehiculoBOImpl : IVehiculoBO
    {
        private VehiculoDAO daoVehiculo;

        public VehiculoBOImpl()
        {
            daoVehiculo = new VehiculoImpl();
        }
        public BindingList<Vehiculo> listarVehiculosPorIDPropietario(int idPropietario)
        {
            return daoVehiculo.listarVehiculosPorIDPropietario(DBManager.Instance.Hostname, DBManager.Instance.Database, DBManager.Instance.Port, DBManager.Instance.Username, DBManager.Instance.Password, idPropietario);
        }
        public BindingList<Vehiculo> listarVehiculosSinPropietarioAsignado()
        {
            return daoVehiculo.listarVehiculosSinPropietarioAsignado(DBManager.Instance.Hostname, DBManager.Instance.Database, DBManager.Instance.Port, DBManager.Instance.Username, DBManager.Instance.Password);
            Vehiculo veh;

        }
    }
}
