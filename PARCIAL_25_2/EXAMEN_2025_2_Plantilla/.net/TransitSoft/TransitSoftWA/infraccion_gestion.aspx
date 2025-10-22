<%@ Page Title="" Language="C#" MasterPageFile="~/TransitSoft.Master" AutoEventWireup="true" CodeBehind="infraccion_gestion.aspx.cs" Inherits="TransitSoftWA.infraccion_gestion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestión de Infracciones
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="card">
        <div class="card-header">
            <h2>Creación de Infracción</h2>
        </div>
        <div class="card-body">
            <div class="mb-3 row">
                <asp:Label ID="lblPlaca" runat="server" Text="Placa" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtPlaca" Enabled="true" runat="server" placeHolder="Texto" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblVelocidad" runat="server" Text="Velocidad" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtVelocidad" Enabled="true" runat="server" placeHolder="Numero" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblLimite" runat="server" Text="lLimite" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtLimite" Enabled="true" runat="server" placeHolder="Numero" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblExceso" runat="server" Text="Exceso" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtExceso" Enabled="true" runat="server" placeHolder="Numero" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblMarcaVehiculo" runat="server" Text="Marca Vehículo" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtMarcaVehiculo" Enabled="true" runat="server" placeHolder="Texto" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblModeloVehiculo" runat="server" Text="Modelo Vehículo" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtModeloVehiculo" Enabled="true" runat="server" placeHolder="Texto"  CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblAnhoVehiculo" runat="server" Text="Año Vehículo" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtAnhoVehiculo" Enabled="true" runat="server" placeHolder="Numero" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblDniPropietario" runat="server" Text="Dni Propietario" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtDniPropietario" Enabled="true" runat="server" placeHolder="Texto" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblNombresPropietario" runat="server" Text="Nombres Propietario" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtNombresPropietario" Enabled="true" runat="server" placeHolder="Texto" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblApellidosPropietario" runat="server" Text="Apellidos Propietario" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtApellidosPropietario" Enabled="true" runat="server" placeHolder="Texto" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblDireccionPropietario" runat="server" Text="Direccion Propietario" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtDireccionPropietario" Enabled="true" runat="server" placeHolder="Texto" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblModeloCamara" runat="server" Text="Modelo Camara" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtModeloCamara" Enabled="true" runat="server" placeHolder="Texto" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblCodigoSerieCamara" runat="server" Text="Codigo Serie Camara" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtCodigoSerieCamara" Enabled="true" runat="server" placeHolder="Texto" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblLatitud" runat="server" Text="Latitud" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtLatitud" Enabled="true" runat="server" placeHolder="Numero" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblLongitud" runat="server" Text="Longitud" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtLongitud" Enabled="true" runat="server" placeHolder="Numero" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblMonto" runat="server" Text="Monto" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtMonto" Enabled="true" runat="server" placeHolder="Numero" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblFechaCaptura" runat="server" Text="Fecha Captura" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtFechaCaptura" Enabled="true" runat="server" placeHolder="Numero" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblFechaRegistro" runat="server" Text="Fecha Registro" CssClass="col-sm-4 col-form-label"></asp:Label>
                <div class="col-sm-4">
                    <asp:TextBox ID="txtFechaRegistro" Enabled="true" runat="server" placeHolder="Numero" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
        </div>
        <div class="card-footer">
             <asp:Button ID="btnRegresar" runat="server" CssClass="float-start btn btn-primary" Text="Regresar" OnClick="btnRegresar_Click"/>
             <asp:Button ID="btnGuardar" runat="server" CssClass="float-end btn btn-secondary" Text="Guardar" OnClick="btnGuardar_Click"/>
        </div>
    </div>
</asp:Content>
