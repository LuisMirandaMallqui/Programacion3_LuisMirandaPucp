/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.test;

import java.util.ArrayList;
import pe.edu.pucp.daoImp.PropietarioDaoImpl;
import pe.edu.pucp.model.PropietarioDto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.dao.PropietarioDao;

/**
 *
 * @author luism
 */
public class PropietarioDaoTest {

    private Integer numero = 1;
    private PropietarioDao propietarioDAO;

    public PropietarioDaoTest() {
        this.propietarioDAO = new PropietarioDaoImpl(2);
    }

    @Test
    public void testInsertar() {
        System.out.println("insertar");
        ArrayList<Integer> listaPropietarioId = new ArrayList<>();
        insertarPropietarios(listaPropietarioId);
        eliminarTodo();
    }

    private void insertarPropietarios(ArrayList<Integer> listaPropietarioId) {
        PropietarioDto propietario = new PropietarioDto();
        propietario.setNombres("Luis");
        propietario.setApellidos("Miranda");
        propietario.setDireccion("Avenida Micaela Dpto 1206");
        propietario.setDni(numero.toString());
        Integer resultado = this.propietarioDAO.insertar(propietario);
        assertTrue(resultado != 0);
        listaPropietarioId.add(resultado);
        this.numero++;
        propietario.setNombres("Luis1");
        propietario.setApellidos("Miranda1");
        propietario.setDireccion("Avenida Micaela Dpto 1206");
        propietario.setDni(numero.toString());
        resultado = this.propietarioDAO.insertar(propietario);
        assertTrue(resultado != 0);
        listaPropietarioId.add(resultado);
        this.numero++;
    }

    @Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        ArrayList<Integer> listaPropietarioId = new ArrayList<>();
        insertarPropietarios(listaPropietarioId);
        PropietarioDto propietario = this.propietarioDAO.obtenerPorId(listaPropietarioId.get(0));
        assertEquals(propietario.getId(), listaPropietarioId.get(0));

        propietario = this.propietarioDAO.obtenerPorId(listaPropietarioId.get(1));
        assertEquals(propietario.getId(), listaPropietarioId.get(1));

        eliminarTodo();
    }

    @Test
    public void testListarTodos() {
        System.out.println("listarTodos");
        ArrayList<Integer> listaPropietarioId = new ArrayList<>();
        insertarPropietarios(listaPropietarioId);

        ArrayList<PropietarioDto> listaPropietario = this.propietarioDAO.listarTodos();
        assertEquals(listaPropietarioId.size(), listaPropietario.size());
        for (Integer i = 0; i < listaPropietarioId.size(); i++) {
            assertEquals(listaPropietarioId.get(i), listaPropietario.get(i).getId());
        }
        eliminarTodo();
    }

    @Test
    public void testModificar() {
        System.out.println("modificar");
        ArrayList<Integer> listaPropietarioId = new ArrayList<>();
        insertarPropietarios(listaPropietarioId);

        ArrayList<PropietarioDto> listaPropietario = this.propietarioDAO.listarTodos();
        assertEquals(listaPropietarioId.size(), listaPropietario.size());
        for (Integer i = 0; i < listaPropietarioId.size(); i++) {
            listaPropietario.get(i).setNombres("NuevoNombre" + i.toString());
            listaPropietario.get(i).setApellidos("NuevoNombre" + i.toString());
            listaPropietario.get(i).setDireccion("NuevoNombre" + i.toString());
            listaPropietario.get(i).setDni("21421" + i.toString());
            this.propietarioDAO.modificar(listaPropietario.get(i));
        }

        ArrayList<PropietarioDto> listaPropietarioModificados = this.propietarioDAO.listarTodos();
        assertEquals(listaPropietario.size(), listaPropietarioModificados.size());
        for (Integer i = 0; i < listaPropietario.size(); i++) {
            assertEquals(listaPropietario.get(i).getNombres(), listaPropietarioModificados.get(i).getNombres());
        }
        eliminarTodo();
    }

    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        ArrayList<Integer> listaPropietarioId = new ArrayList<>();
        insertarPropietarios(listaPropietarioId);
        eliminarTodo();
    }

    private void eliminarTodo() {
        ArrayList<PropietarioDto> listaPropietario = this.propietarioDAO.listarTodos();
        for (Integer i = 0; i < listaPropietario.size(); i++) {
            Integer resultado = this.propietarioDAO.eliminar(listaPropietario.get(i));
            assertNotEquals(0, resultado);
            PropietarioDto propietario = this.propietarioDAO.obtenerPorId(listaPropietario.get(i).getId());
            assertNull(propietario);
        }
    }
}
