package pe.edu.pucp.transitsoft.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import pe.edu.pucp.transitsoft.util.Cifrado;

public class DBManager {

    private static final String ARCHIVO_CONFIGURACION = "jdbc.properties";

    private Connection conexion;
    private String driver;
    private String tipo_de_driver;
    private String base_de_datos;
    private String nombre_de_host;
    private String puerto;
    private String usuario;
    private String contraseña;
    private static DBManager dbManager = null;

    private DBManager() {
        //constructor privado para evitar que se creen instancias.
        //Solo se podrá crear una instancia y esta debe hacerse usando el 
        //método getInstance()
    }

    public static DBManager getInstance() {
        if (DBManager.dbManager == null) {
            DBManager.createInstance();
        }
        return DBManager.dbManager;
    }

    private static void createInstance() {
        if (DBManager.dbManager == null) {
            DBManager.dbManager = new DBManager();
            DBManager.dbManager.leer_archivo_de_propiedades();
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(this.driver);
            this.conexion = DriverManager.getConnection(getURL(), this.usuario, Cifrado.descifrarMD5(this.contraseña));
        } catch (ClassNotFoundException ex) {
            System.err.println("Error al generar la conexión - " + ex);
        }
        return conexion;
    }

    private String getURL() {
        String url = this.tipo_de_driver.concat("://");
        url = url.concat(this.nombre_de_host);
        url = url.concat(":");
        url = url.concat(this.puerto);
        url = url.concat("/");
        url = url.concat(this.base_de_datos);
        //System.out.println(url);
        return url;
    }

    private void leer_archivo_de_propiedades() {
        Properties properties = new Properties();
        try {
            String nmArchivoConf = "/password.txt";
            InputStream inputStream = this.getClass().getResourceAsStream(nmArchivoConf);

            if (inputStream == null) {
                System.err.println("No se pudo encontrar el archivo password.txt en los recursos.");
                return;
            }
            properties.load(inputStream);

            String contrasena = properties.getProperty("contrasenha");
            if (contrasena != null) {
                this.contraseña = contrasena; // Corregiremos esto en el siguiente punto
                //System.out.println(this.contraseña);
//                System.out.println(Cifrado.descifrarMD5(this.contraseña));
            } else {
                System.err.println("No se encontró la clave 'contrasenha' en el archivo password.txt.");
            }

            nmArchivoConf = "/jdbc.properties";
            inputStream = this.getClass().getResourceAsStream(nmArchivoConf);
            if (inputStream == null) {
                System.err.println("No se pudo encontrar el archivo jdbc.properties en los recursos.");
                return;
            }
            properties.load(inputStream);

            this.driver = properties.getProperty("driver");
            this.tipo_de_driver = properties.getProperty("tipo_de_driver");
            this.base_de_datos = properties.getProperty("base_de_datos");
            this.nombre_de_host = properties.getProperty("nombre_de_host");
            this.puerto = properties.getProperty("puerto");
            this.usuario = properties.getProperty("usuario");

        } catch (IOException ex) {
            System.err.println("Error al leer los archivos de propiedades - " + ex);
        }
    }

    public String retornarSQLParaUltimoAutoGenerado() {
        return "select @@last_insert_id as id";
    }

}
