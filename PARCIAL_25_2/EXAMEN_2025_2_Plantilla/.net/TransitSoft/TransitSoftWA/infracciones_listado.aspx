<%@ Page Title="" Language="C#" MasterPageFile="~/TransitSoft.Master" AutoEventWireup="true" CodeBehind="infracciones_listado.aspx.cs" Inherits="TransitSoftWA.infracciones_listado" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Mantenimiento de infracciones 
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <h2>Mantenimiento de infracciones</h2>
        <div class="container row">
            <asp:GridView ID="dgvInfraccion" runat="server" AllowPaging="false" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
                <Columns>
                    <asp:BoundField HeaderText="Id-Infraccion" DataField="Id"/>
                    <asp:BoundField HeaderText="Placa-Vehículo" DataField="Placa"/>
                    <asp:BoundField HeaderText="Dni-Propietario" DataField="DniPropietario"/>
                    <asp:BoundField HeaderText="Velocidad" DataField="Velocidad"/>
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton ID="lbDetalle" runat="server" Text='<i class="fa-solid fa-eye"></i>' CommandArgument='<%# Eval("Id") %>' OnClick="lbDetalle_Click"/>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <div class="container clm">
            <asp:Button ID="btnRegresar" runat="server" CssClass="float-start btn btn-primary" Text="Regresar" OnClick="btnRegresar_Click" />
            <asp:Button ID="btnInsertar" runat="server" CssClass="float-end btn btn-secondary" Text="Insertar" OnClick="btnInsertar_Click" />
        </div>
    </div>
</asp:Content>
