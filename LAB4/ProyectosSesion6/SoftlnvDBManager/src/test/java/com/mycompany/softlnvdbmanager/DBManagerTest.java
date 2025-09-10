/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softlnvdbmanager;

import java.sql.Connection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DBManagerTest {

    public DBManagerTest() {
    }

    @org.junit.jupiter.api.Test
    public void testGetInstance() {
        // TODO review the generated test code and remove the default call to fail.
//        no se puede hacer porque el constructor es privado
//        DBManager dbManager = new DBManager();
        System.out.println("getInstance");
        DBManager dbManager = DBManager.getInstance();
        assertNotNull(dbManager);
    }

    @org.junit.jupiter.api.Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DBManager dbManager = DBManager.getInstance();
        Connection conexion = dbManager.getConnection();
        assertNotNull(conexion);
    }
}
