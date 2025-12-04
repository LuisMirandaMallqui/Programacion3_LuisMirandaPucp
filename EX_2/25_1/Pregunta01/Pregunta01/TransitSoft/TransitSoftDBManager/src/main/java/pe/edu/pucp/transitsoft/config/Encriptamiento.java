
package pe.edu.pucp.transitsoft.config;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encriptamiento {
    
    public static void main(String[] args) throws Exception{
        String passwordSinEncriptar = "Ivannafatima978";
        String clave = "P3MEtPB2wdUXrgoAkrMqEw==";
        String passwordEncriptado = encriptar(passwordSinEncriptar,clave);
        System.out.println("El password encriptado es: " + passwordEncriptado);
    }
    
    // Para generar una clave
    public static String generarClave() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
    
    //Para encriptar el password
    public static String encriptar(String password, String base64Key) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encriptado = cipher.doFinal(password.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(encriptado);
    }
    
    // Para descencriptar el password
    public static String desencriptar(String textoEncriptado, String base64Key) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] descifrado = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));

        return new String(descifrado, "UTF-8");
    }
}