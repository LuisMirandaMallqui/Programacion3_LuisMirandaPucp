using PUCP.TransitSoft.Modelo;
using PUCP.TransitSoft.Negocio.BO;
using PUCP.TransitSoft.Persistencia.DAO;
using PUCP.TransitSoft.Persistencia.DAOImpl;
using System.Collections.Generic;

namespace PUCP.TransitSoft.Negocio.BOImpl {
    public class InfraccionBOImpl : IInfraccionBO {
        private readonly IInfraccionDAO infraccionDao;

        public InfraccionBOImpl() { 
            this.infraccionDao = new InfraccionDAOImpl();
        }

        public List<Infraccion> Listar() {
            IInfraccionDAO dao = new InfraccionDAOImpl();
            return dao.LeerTodos();
        }

        public int Crear(Infraccion modelo) {
            IInfraccionDAO dao = new InfraccionDAOImpl();
            return dao.Crear(modelo);
        }
    }
}
