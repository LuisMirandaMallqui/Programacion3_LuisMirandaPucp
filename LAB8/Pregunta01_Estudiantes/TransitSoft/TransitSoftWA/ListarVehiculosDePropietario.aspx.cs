using DataAccess.Domain;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TransitSoftBusiness.BO;
using TransitSoftBusiness.BOI;

namespace TransitSoftWA
{
    /* Escriba su codigo y nombre completo
    Codigo PUCP: 20223796
    Nombre Completo: Luis Miranda Mallqui
    */
    public partial class ListarVehiculosDeConductor : System.Web.UI.Page
    {
        private IPropietarioBO propietarioBO;
        private BindingList<Propietario> propietarios;
        protected void Page_Load(object sender, EventArgs e)
        {
            propietarioBO = new PropietarioBOImpl();
            propietarios = propietarioBO.listarPropietariosConVehiculosAsignados();
            dgvPropietarios.DataSource = propietarios;
            dgvPropietarios.DataBind();
        }

        protected void lbAsignarVehAProp_Click(object sender, EventArgs e)
        {
            Response.Redirect("AsignarVehiculosAPropietario.aspx"); ;
        }

        protected void dgvConductores_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "Dni").ToString();
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "NombreCompleto").ToString();
            }
        }

        protected void dgvConductores_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvPropietarios.PageIndex = e.NewPageIndex;
            dgvPropietarios.DataBind();
        }

        protected void lkVerVehiculos_Click(object sender, EventArgs e)
        {
            int idPropietario = Int32.Parse(((LinkButton)sender).CommandArgument);
            Session["propietario"] = propietarios.Single(x => x.Id == idPropietario);
            Response.Redirect("AsignarVehiculosAPropietario.aspx?accion=visualizar");
        }
    }
}