package pe.edu.pucp.transitsoft.daoimpl;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.transitsoft.config.DBManager;
import pe.edu.pucp.transitsoft.dao.IConductorDAO;
import pe.edu.pucp.transitsoft.model.Conductor;
import pe.edu.pucp.transitsoft.model.Vehiculo;

public class ConductorDAOImpl implements IConductorDAO {  

    @Override
    public void insertar(Conductor conductor) {
        String sql = "{CALL INSERTAR_CONDUCTOR(?,?,?,?,?,?)}";
        try (
                Connection conn = DBManager.getInstance().getConnection();
                CallableStatement cmd = conn.prepareCall(sql);) {
//            cmd.setString(1, conductor.getPaterno());
//            cmd.setString(2, conductor.getMaterno());
//            cmd.setString(3, conductor.getNombres());
//            cmd.setString(4, conductor.getNumLicencia());
           // cmd.setInt(5, conductor.getTipoLicencia().getTipoLicenciaId());
            cmd.setInt(6, conductor.getPuntosAcumulados());
            cmd.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar conductor", e);
        }
    }

    private Conductor mapearConductor(ResultSet rs) throws SQLException {
        Conductor conductor = new Conductor();
        conductor.setConductorId(rs.getInt("CONDUCTOR_ID"));
        conductor.setPaterno(rs.getString("PATERNO"));
        conductor.setMaterno(rs.getString("MATERNO"));
        conductor.setNombres(rs.getString("NOMBRES"));
        conductor.setNumLicencia(rs.getString("NUM_LICENCIA"));

        // Crear una nueva instancia de TipoLicencia
   
      
      
        conductor.setPuntosAcumulados(rs.getInt("PUNTOS_ACUMULADOS"));
        
        return conductor;
    }

    @Override
    public List<Conductor> listarTodosLicencia() {
        List<Conductor> conductores = new ArrayList<>();
        String sql = "SELECT c.CONDUCTOR_ID, c.PATERNO, c.MATERNO, c.NOMBRES, " +
                "c.NUM_LICENCIA, c.TIPO_LICENCIA_ID, tl.NOMBRE as TIPO_LICENCIA_NOMBRE, c.PUNTOS_ACUMULADOS " +
                "FROM EX2_CONDUCTORES c " +
                "INNER JOIN EX2_TIPOS_LICENCIAS tl ON c.TIPO_LICENCIA_ID = tl.TIPO_LICENCIA_ID";

        try (
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                conductores.add(mapearConductor(rs));
            }
            return conductores;
        } catch (Exception e) {
            throw new RuntimeException("Error al listar conductores", e);
        }
    }

    @Override
    public List<Vehiculo> listarTodosVehiculos(int idConductor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}