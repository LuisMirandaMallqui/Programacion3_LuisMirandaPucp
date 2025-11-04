package pe.edu.pucp.db.util;

//import pe.edu.pucp.softinv.db.util.Cifrado;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.db.util.Cifrado;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author andres
 */
public class CifradoTest {

    public CifradoTest() {
    }

    /**
     * Test of cifrarMD5 method, of class Cifrado.
     */
    @Test
    public void testContraseña() {
        System.out.println(Cifrado.cifrarMD5("pacoflaco123"));
    }

    
    @Test
    public void testCifrarMD5() {
        System.out.println("cifrarMD5");
        String texto = "pacoflaco123";
        String resultado = Cifrado.cifrarMD5(texto);
        String resultado_esperado = "Jdz8vnbsdGVmFPdHIiYWrg==";
        assertEquals(resultado_esperado, resultado);
    }

    /**
     * Test of descifrarMD5 method, of class Cifrado.
     */
    @Test
    public void testDescifrarMD5() {
        System.out.println("descifrarMD5");
        String textoEncriptado = "Jdz8vnbsdGVmFPdHIiYWrg==";
        String resultado_esperado = "pacoflaco123";
        String resultado = Cifrado.descifrarMD5(textoEncriptado);
        System.out.println(resultado);
        assertEquals(resultado_esperado, resultado);
    }

    
    
//    @Test
//    public void testCifrarMD5() {
//        System.out.println("cifrarMD5");
//        String texto = "pacoflaco123";
//        String resultado = Cifrado.cifrarMD5(texto);
//        String resultado_esperado = "GFvzT/oALwhgBEPlFFB2EA==";
//        assertEquals(resultado_esperado, resultado);
//    }
//
//    /**
//     * Test of descifrarMD5 method, of class Cifrado.
//     */
//    @Test
//    public void testDescifrarMD5() {
//        System.out.println("descifrarMD5");
//        String textoEncriptado = "GFvzT/oALwhgBEPlFFB2EA==";
//        String resultado_esperado = "Programacion3!";
//        String resultado = Cifrado.descifrarMD5(textoEncriptado);
//        System.out.println(resultado);
//        assertEquals(resultado_esperado, resultado);
//    }

}
