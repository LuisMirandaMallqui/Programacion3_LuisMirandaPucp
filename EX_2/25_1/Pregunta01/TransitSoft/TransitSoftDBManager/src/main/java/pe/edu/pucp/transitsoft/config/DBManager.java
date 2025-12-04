package pe.edu.pucp.transitsoft.config;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Base64;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
public class DBManager {
    //Patrón Singleton
    private static DBManager dbManager;
    
    private Properties datos;
    private final String hostname;
    private final String usuario;
    private String password;
    private final String clave;
    private final String database;
    private final String puerto;
    private final String url;
    private final String tipoBD;
    private Connection con;
    private String rutaArchivo = "db.properties";
    private ResultSet rs;
    
    private DBManager(){
        //Lectura del archivo properties
        datos = new Properties();
        try{
            InputStream inputStream = 
                Thread.currentThread().getContextClassLoader().getResourceAsStream(rutaArchivo);
            if (inputStream==null) {
                System.err.println("⚠️ ¡NO se encontró db.properties en el classpath!");
            }
            datos.load(inputStream);

        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }


        //Asignamos valores del archivo leido
        hostname = datos.getProperty("hostname");
        usuario = datos.getProperty("usuario");
        //password = datos.getProperty("password");
        password = datos.getProperty("passwordencryptado");
        clave = datos.getProperty("clave");
        password = desencriptar(password,clave);
        puerto = datos.getProperty("puerto");
        database = datos.getProperty("database");
        tipoBD = datos.getProperty("tipoBD");
        
        if(tipoBD.equals("mysql"))
            //Formamos la URL de conexión        
            this.url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + database + "?useSSL=false";
        else 
            this.url = "jdbc:sqlserver://" + hostname + 
                    ";encrypt=false;trustServerCertificate=true;databaseName=" + database + 
                    ";integratedSecurity=false;";
    }
    
    public String desencriptar(String textoEncriptado, String base64Key) {
        String desencriptado = "";
        try{
            byte[] decodedKey = Base64.getDecoder().decode(base64Key);
            SecretKeySpec secretKey = new SecretKeySpec(decodedKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] descifrado = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));
            desencriptado = new String(descifrado, "UTF-8");
        }catch(UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
            System.out.println(ex.getMessage());
        }
        return desencriptado;
    }
    
    public static synchronized DBManager getInstance(){
        if(dbManager == null)
            dbManager = new DBManager();
        return dbManager;
    }
    
    //Nos permite obtener una conexión con la BD
    public Connection getConnection(){
        try{
            if(con == null || con.isClosed()){
                if(tipoBD.equals("mysql"))
                    Class.forName("com.mysql.cj.jdbc.Driver");
                else if (tipoBD.equals("mssql"))
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(url, usuario, password);
                System.out.println("Se ha establecido la conexion con la BD...");
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
    
    public void cerrarConexion() {
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar el lector:" + ex.getMessage());
            }
        }
        if (con != null) {
            try {
                con.close();  
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión:" + ex.getMessage());
            }
        }
    }
    
    //Métodos para llamadas a Procedimientos Almacenados
    public int ejecutarProcedimiento(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) {
        int resultado = 0;
        try{
            CallableStatement cst = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, parametrosSalida);
            if(parametrosEntrada != null)
                registrarParametrosEntrada(cst, parametrosEntrada);
            if(parametrosSalida != null)
                registrarParametrosSalida(cst, parametrosSalida);
        
            resultado = cst.executeUpdate();
        
            if(parametrosSalida != null)
                obtenerValoresSalida(cst, parametrosSalida);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            cerrarConexion();
        }
        return resultado;
    }
    
    public ResultSet ejecutarProcedimientoLectura(String nombreProcedimiento, Map<Integer,Object> parametrosEntrada){
        try{
            CallableStatement cs = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, null);
            if(parametrosEntrada!=null)
                registrarParametrosEntrada(cs,parametrosEntrada);
            rs = cs.executeQuery();
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
        return rs;
    }
    
    public CallableStatement formarLlamadaProcedimiento(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) throws SQLException{
        con = getConnection();
        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
        int cantParametrosEntrada = 0;
        int cantParametrosSalida = 0;
        if(parametrosEntrada!=null) cantParametrosEntrada = parametrosEntrada.size();
        if(parametrosSalida!=null) cantParametrosSalida = parametrosSalida.size();
        int numParams =  cantParametrosEntrada + cantParametrosSalida;
        for (int i = 0; i < numParams; i++) {
            call.append("?");
            if (i < numParams - 1) {
                call.append(",");
            }
        }
        call.append(")}");
        return con.prepareCall(call.toString());
    }
    
    private void registrarParametrosEntrada(CallableStatement cs, Map<Integer, Object> parametros) throws SQLException {
        for (Map.Entry<Integer, Object> entry : parametros.entrySet()) {
            Integer key = entry.getKey();
            Object value = entry.getValue();
            switch (value) {
                case Integer entero -> cs.setInt(key, entero);
                case String cadena -> cs.setString(key, cadena);
                case Double decimal -> cs.setDouble(key, decimal);
                case Boolean booleano -> cs.setBoolean(key, booleano);
                case java.util.Date fecha -> cs.setDate(key, new java.sql.Date(fecha.getTime()));
                case byte[] archivo -> cs.setBytes(key, archivo);
                case Character caracter -> cs.setString(key, String.valueOf(caracter));
                default -> {
                }
                // Agregar más tipos según sea necesario
            }
        }
    }
    
    private void registrarParametrosSalida(CallableStatement cst, Map<Integer, Object> params) throws SQLException {
        for (Map.Entry<Integer, Object> entry : params.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();
            cst.registerOutParameter(posicion, sqlType);
        }
    }

    private void obtenerValoresSalida(CallableStatement cst, Map<Integer, Object> parametrosSalida) throws SQLException {
        for (Map.Entry<Integer, Object> entry : parametrosSalida.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();
            Object value = null;
            switch (sqlType) {
                case Types.INTEGER -> value = cst.getInt(posicion);
                case Types.VARCHAR -> value = cst.getString(posicion);
                case Types.DOUBLE -> value = cst.getDouble(posicion);
                case Types.BOOLEAN -> value = cst.getBoolean(posicion);
                case Types.DATE -> value = cst.getDate(posicion);
                case Types.BLOB -> value = cst.getBytes(posicion);
                // Agregar más tipos según sea necesario
            }
            parametrosSalida.put(posicion, value);
        }
    }
    
    //Para transacciones
    
    public void iniciarTransaccion() throws SQLException{
        con = getConnection();
        con.setAutoCommit(false);
    }
    
    public void confirmarTransaccion() throws SQLException{
        con.commit();
    }
    
    public void cancelarTransaccion(){
        try{
            con.rollback();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            cerrarConexion();
        }
    }
    
    public CallableStatement formarLlamadaProcedimientoTransaccion(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) throws SQLException{
        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
        int cantParametrosEntrada = 0;
        int cantParametrosSalida = 0;
        if(parametrosEntrada!=null) cantParametrosEntrada = parametrosEntrada.size();
        if(parametrosSalida!=null) cantParametrosSalida = parametrosSalida.size();
        int numParams =  cantParametrosEntrada + cantParametrosSalida;
        for (int i = 0; i < numParams; i++) {
            call.append("?");
            if (i < numParams - 1) {
                call.append(",");
            }
        }
        call.append(")}");
        return con.prepareCall(call.toString());
    }
    
    public int ejecutarProcedimientoTransaccion(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) throws SQLException{
        int resultado;
        
        CallableStatement cst = formarLlamadaProcedimientoTransaccion(nombreProcedimiento, parametrosEntrada, parametrosSalida);
        if (parametrosEntrada != null) {
            registrarParametrosEntrada(cst, parametrosEntrada);
        }
        if (parametrosSalida != null) {
            registrarParametrosSalida(cst, parametrosSalida);
        }

        resultado = cst.executeUpdate();

        if (parametrosSalida != null)
            obtenerValoresSalida(cst, parametrosSalida);

        return resultado;
    }

        public boolean testConnection() {
            try (Connection c = getConnection()) {
                return c != null && c.isValid(2);
            } catch (SQLException ex) {
                System.err.println("Test de conexión fallido: " + ex.getMessage());
                return false;
            }
        }

        /**
         * Testea que la consulta a EX1_INFRACCIONES devuelva al menos una fila.
         */
        public boolean testQuery() {
            String sql = "SELECT INFRACCION_ID, DESCRIPCION, MONTO_MULTA, GRAVEDAD, PUNTOS FROM EX1_INFRACCIONES";
            try (Connection c = getConnection();
                 Statement st = c.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                if (rs.next()) {
                    System.out.println("Consulta OK, primer registro:");
                    System.out.printf("  ID=%d, desc=\"%s\", multa=%.2f, gravedad=\"%s\", puntos=%d%n",
                        rs.getInt("INFRACCION_ID"),
                        rs.getString("DESCRIPCION"),
                        rs.getDouble("MONTO_MULTA"),
                        rs.getString("GRAVEDAD"),
                        rs.getInt("PUNTOS"));
                } else {
                    System.out.println("Consulta OK, pero no hay registros en EX1_INFRACCIONES.");
                }
                return true;

            } catch (SQLException ex) {
                System.err.println("Test de consulta fallido: " + ex.getMessage());
                return false;
            }
        }

        public static void main(String[] args) {
            DBManager mgr = DBManager.getInstance();

            System.out.println("=== Probando conexión ===");
            if (mgr.testConnection()) {
                System.out.println("¡Conexión OK!");
            } else {
                System.err.println("No se pudo conectar a la BD.");
            }

            System.out.println("\n=== Probando SELECT a EX1_INFRACCIONES ===");
            if (mgr.testQuery()) {
                System.out.println("Test de consulta completado.");
            } else {
                System.err.println("Test de consulta FALLIDO.");
            }
        }
}

