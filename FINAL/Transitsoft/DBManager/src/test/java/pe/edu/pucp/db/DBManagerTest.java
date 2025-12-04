/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.db;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.util.Cifrado;


public class DBManagerTest {

//    @Test
//    public void testGetInstance() {
//        System.out.println("getInstance");
//        DBManager dBManager = DBManager.getInstanceBD1();
//        assertNotNull(dBManager);
//
//        dBManager = DBManager.getInstanceBD2();
//        assertNotNull(dBManager);
//    }

    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DBManager dBManager1 = DBManager.getInstanceBD1();
        Connection conexion = dBManager1.getConnection();
        assertNotNull(conexion);

//        DBManager dBManager2 = DBManager.getInstanceBD2();
//        conexion = dBManager2.getConnection();
//        assertNotNull(conexion);
    }
    
//    @Test
//    public void testCifrado(){
//        String password = Cifrado.cifrarMD5("pacoflaco123");
//        System.out.println(password);
//        System.out.println(Cifrado.descifrarMD5(password));
//        
//    }

}
