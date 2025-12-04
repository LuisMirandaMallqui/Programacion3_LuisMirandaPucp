/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.db;

/**
 *
 * @author luism
 */
public class DBManagerMSSQL extends DBManager {

    protected DBManagerMSSQL() {
        super();
    }

    @Override
    protected String getURL() {
        String url = this.datosConexion.getTipo_de_driver().concat("://");
        url = url.concat(this.datosConexion.getNombre_de_host());
        url = url.concat(":");
        url = url.concat(this.datosConexion.getPuerto());
        url = url.concat(";");
        url = url.concat("databaseName=" + this.datosConexion.getBase_de_datos());
        url = url.concat(";encrypt=false");
        return url;
    }

    @Override
    public String retornarSQLParaUltimoAutoGenerado() {
        String sql = "SELECT @@IDENTITY AS ID";
        return sql;
    }

}
