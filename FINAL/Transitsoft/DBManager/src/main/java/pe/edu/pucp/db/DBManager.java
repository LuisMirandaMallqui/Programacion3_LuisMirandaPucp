package pe.edu.pucp.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import pe.edu.pucp.util.Cifrado;
import pe.edu.pucp.util.DatosConexion;

/**
 * @author luis miranda 20223796
 */
public abstract class DBManager {

    private static final String ARCHIVO_DE_CONFIGURACION_BD1 = "jdbc.bd1.properties";
    private static final String ARCHIVO_DE_CONFIGURACION_BD2 = "jdbc.bd2.properties";

    //Dos instancias Singleton
    private static DBManager dbManager1 = null;
    private static DBManager dbManager2 = null;

    //Cada instancia ahora cuenta con su propia conexión y datos
    private Connection conexion;
    protected DatosConexion datosConexion;

    protected DBManager() {
        this.datosConexion = new DatosConexion();
    }

    public static DBManager getInstanceBD1() {
        if (DBManager.dbManager1 == null) {
            DBManager.createInstance(ARCHIVO_DE_CONFIGURACION_BD1);
        }
        return DBManager.dbManager1;
    }

    public static DBManager getInstanceBD2() {
        if (DBManager.dbManager2 == null) {
            DBManager.createInstance(ARCHIVO_DE_CONFIGURACION_BD2);
        }
        return DBManager.dbManager2;
    }

    private static void createInstance(String nombreArchivo) {
        DBManager nuevaInstancia = null;
        Properties properties = new Properties();
        try {
            properties.load(DBManager.class.getResourceAsStream("/" + nombreArchivo));
            // Leemos una nueva propiedad "motor" que agregada al .properties
            // Ejemplo en el archivo: tipo_motor=MYSQL
            String tipoMotor = properties.getProperty("tipo_motor");
            switch (tipoMotor.toUpperCase()) {
                case "MYSQL":
                    nuevaInstancia = new DBManagerMySQL();
                    break;
                case "MSSQL":
                    nuevaInstancia = new DBManagerMSSQL();
                    break;
                // Si mañana agregan Oracle, solo añades un case aquí.
                default:
                    throw new IllegalArgumentException("Motor no soportado: " + tipoMotor);
            }
            // Cargamos los datos (usuario, pass, url) en la instancia creada
            nuevaInstancia.cargarDatosDeConexion(properties);

            if (nombreArchivo.equals(ARCHIVO_DE_CONFIGURACION_BD1)) {
                DBManager.dbManager1 = nuevaInstancia;
            } else if (nombreArchivo.equals(ARCHIVO_DE_CONFIGURACION_BD2)) {
                DBManager.dbManager2 = nuevaInstancia;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        } catch (IOException ex) {
            System.err.println("Error leyendo properties: " + ex);
        }
    }

    // Métodos de instancia (los que usan 'this.conexion') 
    public Connection getConnection() {
        try {
            if (this.conexion == null || this.conexion.isClosed()) {
                Class.forName(this.datosConexion.getDriver());
                // Se conecta usando sus propios datos
                this.conexion = DriverManager.getConnection(
                        getURL(),
                        this.datosConexion.getUsuario(),
                        Cifrado.descifrarMD5(this.datosConexion.getContraseña())
                );
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error al generar la conexión - " + ex);
        }
        return this.conexion;
    }

    // Métodos abstractos y auxiliares
    protected abstract String getURL();

    public abstract String retornarSQLParaUltimoAutoGenerado();

    private void cargarDatosDeConexion(Properties properties) {
        this.datosConexion.setDriver(properties.getProperty("driver"));
        this.datosConexion.setTipo_de_driver(properties.getProperty("tipo_de_driver"));
        this.datosConexion.setBase_de_datos(properties.getProperty("base_de_datos"));
        this.datosConexion.setNombre_de_host(properties.getProperty("nombre_de_host"));
        this.datosConexion.setPuerto(properties.getProperty("puerto"));
        this.datosConexion.setUsuario(properties.getProperty("usuario"));
        this.datosConexion.setContraseña(properties.getProperty("contrasenha"));
    }

}
