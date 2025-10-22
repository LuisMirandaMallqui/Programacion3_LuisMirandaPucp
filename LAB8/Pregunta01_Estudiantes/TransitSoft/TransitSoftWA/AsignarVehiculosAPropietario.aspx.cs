using DataAccess.Domain;
using DataAccess.Persistance;
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
    Nombre Completo: Luis Miranda
    */
    public partial class AsignarVehiculosAConductor : System.Web.UI.Page
    {
        private Propietario propietario;
        private IPropietarioBO propietarioBO;
        private IVehiculoBO vehiculoBO;
        protected void Page_Load(object sender, EventArgs e)
        {

            vehiculoBO = new VehiculoBOImpl();
            propietarioBO = new PropietarioBOImpl();

            // ESTE "IF" ES CRÍTICO
            // Propiedad de la página que te dice si la página se está cargando por un clic de botón (true) o si es la primera vez que el usuario la visita (false).
            if (!IsPostBack) 
            { 
                String accion = Request.QueryString["accion"];
                if (accion == null)
                {
                    lblTitulo.Text = "Asignar Vehículos a Propietario";
                    // 2. Cargar la lista de propietarios
                    CargarPropietarios();
                    // 3. Cargar la lista de vehículos
                    CargarVehiculos();
                }
                else if (accion == "visualizar")
                {
                    propietario = (Propietario)Session["propietario"];
                    gvVehiculos.DataSource = vehiculoBO.listarVehiculosPorIDPropietario(propietario.Id);
                    gvVehiculos.DataBind();
                    ddlPropietarios.Items.Insert(0, new ListItem(propietario.DNINombreCompleto, propietario.Id.ToString()));
                    ddlPropietarios.SelectedIndex = 0;
                    lblTitulo.Text = "Ver Vehículos del Propietario";
                    ddlPropietarios.Enabled = false;
                    ddlVehiculos.Enabled = false;
                    lbGuardar.Visible = false;
                    lbAgregarVehiculo.Visible = false;
                    gvVehiculos.Enabled = false;
                }
            }
        }

        //privado pues se llama desde esta clase interna, el aspx no lo ve
        private void CargarPropietarios()
        {
            BindingList<Propietario> propietarios;
            //usamos el bindinglist, con Propietario vemos que esta en el ddl (model)
            propietarios = propietarioBO.listarPropietariosSinVehiculoAsignado();
            //aca actualizamos el bo y boImpl agregando las funciones usando metodos del DAO (persistencia)
            ddlPropietarios.DataSource = propietarios;
            ddlPropietarios.DataTextField = "DNINombreCompleto";
            ddlPropietarios.DataValueField = "Id";
            ddlPropietarios.DataBind();
        }

        private void CargarVehiculos()
        {
            BindingList<Vehiculo> vehiculos;
            vehiculos = vehiculoBO.listarVehiculosSinPropietarioAsignado();
            Session["ListaVehiculosCompletos"] = vehiculos;
            ddlVehiculos.DataSource = vehiculos;
            ddlVehiculos.DataTextField = "Datos";
            ddlVehiculos.DataValueField = "Id";
            ddlVehiculos.DataBind();
        }

        protected void btnAgregarVehiculo_Click(object sender, EventArgs e)
        {
            // 1. Obtener el ID del vehículo del DropDownList
            int idVehiculo = int.Parse(ddlVehiculos.SelectedValue);
            // 2. Recuperar la lista COMPLETA de vehículos (que guardaste en CargarVehiculos) sin propietario desde el ViewState
            BindingList<Vehiculo> listaCompleta = (BindingList<Vehiculo>)Session["ListaVehiculosCompletos"];
            // 3. Buscar el objeto Vehiculo completo usando el ID
            Vehiculo vehiculoAAgregar = listaCompleta.FirstOrDefault(v => v.Id == idVehiculo);
            // 4. Recuperar la lista TEMPORAL desde el Session
            List<Vehiculo> listaTemporal = this.VehiculosSeleccionados; // Llama al GET
            // 5. Agregar el vehículo si no está ya en la lista
            if (vehiculoAAgregar != null && !listaTemporal.Any(v => v.Id == idVehiculo))
            {
                listaTemporal.Add(vehiculoAAgregar);
            }
            // 6. Guardar la lista actualizada DE VUELTA en el Session
            this.VehiculosSeleccionados = listaTemporal; // Llama al SET
            // 7. Actualizar el GridView para que el usuario vea el cambio
            ActualizarGridView();
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarVehiculosDePropietario.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            // 1. Obtener la lista final desde el Session
            List<Vehiculo> listaFinal = this.VehiculosSeleccionados;
            // 2. Obtener el ID del propietario seleccionado
            int idPropietario = int.Parse(ddlPropietarios.SelectedValue);
            // 3. Crear el objeto Propietario y asignarle sus vehículos
            Propietario propietario = new Propietario();
            propietario.Id = idPropietario;
            // 4. ¡USAR LA LÍNEA DEL EXAMEN!
            propietario.Vehiculos = new BindingList<Vehiculo>(listaFinal.ToArray());
            // 5. Llamar al método de la DLL para registrar en la BD
            int resultado = propietarioBO.registrarAsignacionDeVehiculosAPropietario(propietario);
            // 6. Limpiar todo
            if (resultado > 0)
            {
                this.VehiculosSeleccionados = new List<Vehiculo>(); // Limpia el ViewState
                ActualizarGridView();
                CargarPropietarios(); // Recargar listas
                CargarVehiculos();
            }
        }

        private void ActualizarGridView()
        {
            gvVehiculos.DataSource = VehiculosSeleccionados;
            gvVehiculos.DataBind();
        }

        protected void gvVehiculos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            // 1. Preguntamos si el botón que se presionó fue el de "Eliminar"
            if (e.CommandName == "Eliminar")
            {
                // 2. Obtenemos el ID del vehículo que enviamos desde el CommandArgument
                int idVehiculo = int.Parse(e.CommandArgument.ToString());

                // 3. Recuperamos la lista temporal desde la Session
                List<Vehiculo> listaTemporal = this.VehiculosSeleccionados;

                // 4. Buscamos el vehículo en la lista usando su ID
                Vehiculo vehiculoAEliminar = listaTemporal.FirstOrDefault(v => v.Id == idVehiculo);

                // 5. Si lo encontramos, lo eliminamos de la lista
                if (vehiculoAEliminar != null)
                {
                    listaTemporal.Remove(vehiculoAEliminar);
                }

                // 6. Guardamos la lista (ya modificada) DE VUELTA en la Session
                this.VehiculosSeleccionados = listaTemporal;

                // 7. Actualizamos el GridView para que el usuario vea el cambio
                ActualizarGridView();
            }
        }

        // Propiedad para manejar la lista temporal en el Session
        private List<Vehiculo> VehiculosSeleccionados
        {
            get
            {
                if (Session["VehiculosAsignados"] == null)
                    Session["VehiculosAsignados"] = new List<Vehiculo>();
                return (List<Vehiculo>)Session["VehiculosAsignados"];
            }
            set
            {
                Session["VehiculosAsignados"] = value;
            }
        }
    }
}