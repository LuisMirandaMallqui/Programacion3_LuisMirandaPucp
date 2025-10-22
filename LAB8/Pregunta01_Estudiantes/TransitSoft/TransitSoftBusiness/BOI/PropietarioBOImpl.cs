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
    public class PropietarioBOImpl : IPropietarioBO
    {
        private PropietarioDAO daoPropietario;

        public PropietarioBOImpl()
        {
            daoPropietario = new PropietarioImpl();
        }

        public BindingList<Propietario> listarPropietariosConVehiculosAsignados()
        {
            return daoPropietario.listarPropietariosConVehiculosAsignados(DBManager.Instance.Hostname,DBManager.Instance.Database,DBManager.Instance.Port,DBManager.Instance.Username,DBManager.Instance.Password);
        }

        public BindingList<Propietario> listarPropietariosSinVehiculoAsignado()
        {
            return daoPropietario.listarPropietariosSinVehiculoAsignado(DBManager.Instance.Hostname, DBManager.Instance.Database, DBManager.Instance.Port, DBManager.Instance.Username, DBManager.Instance.Password);
        }

        public int registrarAsignacionDeVehiculosAPropietario(Propietario Propietario)
        {
            return daoPropietario.registrarAsignacionDeVehiculosAPropietario(DBManager.Instance.Hostname, DBManager.Instance.Database, DBManager.Instance.Port, DBManager.Instance.Username, DBManager.Instance.Password, Propietario);
        }

    }
}
