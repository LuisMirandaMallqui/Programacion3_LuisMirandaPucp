/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.daoImp.util.Columna;
import pe.edu.pucp.model.PropietarioDto; 
import pe.edu.pucp.dao.PropietarioDao;

/**
 *
 * @author alulab14
 */

public class PropietarioDaoImpl extends DaoImplBase implements PropietarioDao {

    private PropietarioDto propietarioDTO;

    public PropietarioDaoImpl(int bdObjetivo) {
        super("propietario",bdObjetivo);
        this.propietarioDTO = null;
        this.retornarLlavePrimaria = true;
    }
    public PropietarioDaoImpl() {
        this(1); // Por defecto los DAO's se conectan a la bd1
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("dni", false, false));
        this.listaColumnas.add(new Columna("nombres", false, false));
        this.listaColumnas.add(new Columna("apellidos", false, false));
        this.listaColumnas.add(new Columna("direccion", false, false));
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.propietarioDTO = new PropietarioDto();
        this.propietarioDTO.setId(this.resultSet.getInt("id"));
        this.propietarioDTO.setDni(this.resultSet.getString("dni"));
        this.propietarioDTO.setNombres(this.resultSet.getString("nombres"));
        this.propietarioDTO.setApellidos(this.resultSet.getString("apellidos"));
        this.propietarioDTO.setDireccion(this.resultSet.getString("direccion"));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        // Orden debe coincidir con el de las columnas NO autogeneradas 
        int i = 1;
        this.statement.setString(i++, this.propietarioDTO.getDni());
        this.statement.setString(i++, this.propietarioDTO.getNombres());
        this.statement.setString(i++, this.propietarioDTO.getApellidos());
        this.statement.setString(i++, this.propietarioDTO.getDireccion());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        int i = 1;
        // SET valores
        this.statement.setString(i++, this.propietarioDTO.getDni());
        this.statement.setString(i++, this.propietarioDTO.getNombres());
        this.statement.setString(i++, this.propietarioDTO.getApellidos());
        this.statement.setString(i++, this.propietarioDTO.getDireccion());
        
        // WHERE id = ?
        this.statement.setInt(i++, this.propietarioDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        // WHERE id = ?
        this.statement.setInt(1, this.propietarioDTO.getId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        // WHERE id = ?
        this.statement.setInt(1, this.propietarioDTO.getId());
    }
    
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.propietarioDTO = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.propietarioDTO);
    }

    
    @Override
    public Integer insertar(PropietarioDto propietario) {        
        this.propietarioDTO = propietario;
        return super.insertar();
    }
    
    @Override
    public PropietarioDto obtenerPorId(Integer propietarioId) {
        this.propietarioDTO = new PropietarioDto();
        this.propietarioDTO.setId(propietarioId);
        super.obtenerPorId();
        return this.propietarioDTO;
    }
    
    @Override
    public ArrayList<PropietarioDto> listarTodos() {
        return (ArrayList<PropietarioDto>) super.listarTodos();
    }
    
    @Override
    public Integer modificar(PropietarioDto propietario) {
        this.propietarioDTO = propietario;
        return super.modificar();
    }
    
    @Override
    public Integer eliminar(PropietarioDto propietario) {
        this.propietarioDTO = propietario;
        return super.eliminar();
    }
}