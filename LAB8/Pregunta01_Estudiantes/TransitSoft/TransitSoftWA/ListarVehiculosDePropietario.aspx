<%@ Page Title="" Language="C#" MasterPageFile="~/TransitSoft.Master" AutoEventWireup="true" CodeBehind="ListarVehiculosDePropietario.aspx.cs" Inherits="TransitSoftWA.ListarVehiculosDeConductor" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_Title" runat="server">
    Listar Vehículos de Propietarios
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_Scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_Contenido" runat="server">
    <div class="container">
        <div class="container row">
            <div class="row align-items-center">
                <div class="col-auto">
                    <asp:Label ID="lblTitulo" CssClass="form-label fw-bold" runat="server" Text="Lista de Propietarios que tienen Vehículos Asignados"></asp:Label>
                </div>
                <div class="col text-end p-3">
                    <asp:LinkButton ID="lbAsignarVehAProp" CssClass="btn btn-success" runat="server" Text="<i class='fa-solid fa-plus pe-2'></i> Asignar Vehiculos a Propietario" OnClick="lbAsignarVehAProp_Click" />
                </div>
            </div>
        </div>
        <div class="container row">
            <asp:GridView ID="dgvPropietarios" runat="server" AutoGenerateColumns="false"
            AllowPaging="true" PageSize="8" OnRowDataBound="dgvConductores_RowDataBound"
            CssClass="table table-hover table-responsive table-striped" OnPageIndexChanging="dgvConductores_PageIndexChanging">
            <Columns>
                <asp:BoundField HeaderText="DNI" ItemStyle-CssClass="align-middle" />
                <asp:BoundField HeaderText="Nombre Completo del Propietario" ItemStyle-CssClass="align-middle" />
                <asp:TemplateField>
                    <ItemTemplate>
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-eye'></i> Ver Vehículos" CssClass="btn btn-info" OnClick="lkVerVehiculos_Click" CommandArgument='<%# Eval("Id")%>'/>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>