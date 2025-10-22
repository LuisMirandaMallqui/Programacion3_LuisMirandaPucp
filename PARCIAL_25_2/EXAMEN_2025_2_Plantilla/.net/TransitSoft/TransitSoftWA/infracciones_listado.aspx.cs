using PUCP.TransitSoft.Modelo;
using PUCP.TransitSoft.Negocio.BO;
using PUCP.TransitSoft.Negocio.BOImpl;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace TransitSoftWA
{
    public partial class infracciones_listado : System.Web.UI.Page
    {
        private IInfraccionBO infraccionBO;
        private IList<Infraccion> infraccionList;

        public IInfraccionBO InfraccionBO { get => infraccionBO; set => infraccionBO = value; }
        public IList<Infraccion> InfraccionList { get => infraccionList; set => infraccionList = value; }

        public infracciones_listado()
        {
            this.InfraccionBO = new InfraccionBOImpl();
            this.InfraccionList = this.InfraccionBO.Listar();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            dgvInfraccion.DataSource = this.InfraccionList;
            dgvInfraccion.DataBind();
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            this.IrAIndex();
        }

        protected void btnInsertar_Click(object sender, EventArgs e)
        {
            Response.Redirect("infraccion_gestion.aspx");
        }

        protected void lbDetalle_Click(object sender, EventArgs e)
        {

        }

        private void IrAIndex()
        {
            Response.Redirect("Index.aspx");
        }
    }
}