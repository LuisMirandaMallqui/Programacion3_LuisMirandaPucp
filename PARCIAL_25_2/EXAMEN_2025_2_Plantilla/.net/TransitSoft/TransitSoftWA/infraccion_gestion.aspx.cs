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
    public partial class infraccion_gestion : System.Web.UI.Page
    {
        private IInfraccionBO infraccionBO;
        private Infraccion infraccion;

        public infraccion_gestion()
        {
            this.infraccionBO = new InfraccionBOImpl();
            this.infraccion = new Infraccion();
        }

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            this.IrAListado();
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            this.infraccion.Placa = txtPlaca.Text;
            this.infraccion.Velocidad = Convert.ToDouble(txtVelocidad.Text);
            this.infraccion.Limite = Convert.ToDouble(txtLimite.Text);
            this.infraccion.Exceso = Convert.ToDouble(txtExceso.Text);
            this.infraccion.MarcaVehiculo = txtMarcaVehiculo.Text;
            this.infraccion.ModeloVehiculo = txtModeloVehiculo.Text;
            this.infraccion.AnhoVehiculo = Convert.ToInt32(txtAnhoVehiculo.Text);
            this.infraccion.DniPropietario = txtDniPropietario.Text;
            this.infraccion.NombresPropietario = txtNombresPropietario.Text;
            this.infraccion.ApellidosPropietario = txtApellidosPropietario.Text;
            this.infraccion.DireccionPropietario = txtDireccionPropietario.Text;
            this.infraccion.ModeloCamara = txtModeloCamara.Text;
            this.infraccion.CodigoSerieCamara = txtCodigoSerieCamara.Text;
            this.infraccion.Latitud = Convert.ToInt64(txtLatitud.Text);
            this.infraccion.Longitud = Convert.ToInt64(txtLongitud.Text);
            this.infraccion.Monto = Convert.ToDouble(txtMonto.Text);
            this.infraccion.FechaCapturaTimestamp = Convert.ToInt64(txtFechaCaptura.Text);
            this.infraccion.FechaRegistroTimestamp = Convert.ToInt64(txtFechaRegistro.Text);
            this.infraccionBO.Crear(infraccion);
            this.IrAListado();
        }

        private void IrAListado()
        {
            Response.Redirect("infracciones_listado.aspx");
        }

    }
}