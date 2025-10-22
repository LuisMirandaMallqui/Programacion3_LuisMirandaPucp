<%@ Page Title="" Language="C#" MasterPageFile="~/TransitSoft.Master" AutoEventWireup="true" CodeBehind="AsignarVehiculosAPropietario.aspx.cs" Inherits="TransitSoftWA.AsignarVehiculosAConductor" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Asignar Vehículos a Propietario
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <asp:UpdatePanel ID="upContenedor" runat="server" UpdateMode="Conditional">
        <ContentTemplate>
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <%--el titulo se da en lblTitulo.text en el aspx.cs--%>
                        <h2><asp:Label ID="lblTitulo" runat="server" Text="Label"></asp:Label></h2>
                    </div>
                    <div class="card-body">
                        <div class="card border">
                            <div class="card-header bg-light">
                                <h5 class="card-title mb-0 fw-bold">Datos del Propietario</h5>
                            </div>
                            <div class="card-body">
                                <div class="mb-3 row">
                                    <asp:Label ID="lblDatosConductor" runat="server" Text="Seleccione el Conductor:" CssClass="col-sm-12 col-form-label"></asp:Label>
                                    <div class="col-sm-12">
                                        <asp:DropDownList ID="ddlPropietarios" CssClass="form-select" runat="server"></asp:DropDownList>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card border">
                            <div class="card-header bg-light">
                                <h5 class="card-title mb-0 fw-bold">Lista de Vehículos del Propietario</h5>
                            </div>
                            <div class="card-body">
                                <div class="mb-3 row">
                                    <asp:Label ID="lblVehiculo" runat="server" Text="Seleccione el Vehículo:" CssClass="col-sm-12 col-form-label"></asp:Label>
                                    <div class="col-sm-9">
                                        <asp:DropDownList ID="ddlVehiculos" CssClass="form-select" runat="server"></asp:DropDownList>
                                    </div>
                                    <div class="col-sm-3">
                                        <asp:LinkButton ID="lbAgregarVehiculo" runat="server" Text="<i class='fa-solid fa-plus pe-2'></i> Agregar Vehiculo" CssClass="btn btn-success" OnClick ="btnAgregarVehiculo_Click" />
                                    </div>
                                </div>
                                <div class="row">
                                    <asp:GridView ID="gvVehiculos" runat="server"
                                        AllowPaging="true" PageSize="5" AutoGenerateColumns="false"
                                        CssClass="table table-hover table-responsive table-striped" 
                                        OnRowCommand="gvVehiculos_RowCommand">
                                        <Columns>
                                            <asp:BoundField HeaderText="Marca" DataField="Marca" />
                                            <asp:BoundField HeaderText="Modelo" DataField="Modelo"/>
                                            <asp:BoundField HeaderText="Placa" DataField="Placa"/>
                                            <asp:BoundField HeaderText="Año" DataField="Anho"/>
                                            <asp:TemplateField>
                                                <ItemTemplate>
                                                    <asp:LinkButton ID="lbBorrarLOV" runat="server" Text="<i class='fa-solid fa-trash'></i>" CommandName="Eliminar" CommandArgument='<%# Eval("Id") %>'/>
                                                </ItemTemplate>
                                            </asp:TemplateField>
                                        </Columns>
                                    </asp:GridView>
                                </div>
                            </div>    
                        </div>
                    </div>
                    <div class="card-footer clearfix">
                        <asp:LinkButton ID="lbRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left pe-2'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="btnRegresar_Click"/>
                        <%--por completar guardar--%>
                        <asp:LinkButton ID="lbGuardar" runat="server" Text="<i class='fa-solid fa-floppy-disk pe-2'></i> Guardar" CssClass="float-end btn btn-primary" OnClick="btnGuardar_Click"  />
                    </div>
                </div>
            </div>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>