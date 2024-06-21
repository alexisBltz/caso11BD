package org.app;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoTrabajadorDAO {
    public void createtipoTrabajador(TipoTrabajador tipoTrabajador){
        String sql ="INSERT INTO td_tipotrabajador (TrabajaTipoTraId, TipTrabDesc, TipTrabEstReg) VALUES (?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
                pstmt.setString(1, String.valueOf(tipoTrabajador.getTrabajadortipoTraId()));
                pstmt.setString(2, tipoTrabajador.getTibtrabDesc());
                pstmt.setString(3, String.valueOf(tipoTrabajador.getTrabEstReg()));
                pstmt.executeUpdate();
                System.out.println("Tipo Trabajador creado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<TipoTrabajador> readTipoTrabajadores(){
        List<TipoTrabajador> tipoTrabajadores = new ArrayList<>();
        String sql = "SELECT * FROM td_tipotrabajador";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoTrabajador tipotrabajador = new TipoTrabajador();
                tipotrabajador.setTrabajadortipoTraId(rs.getString("TrabajaTipoTraId").charAt(0));
                tipotrabajador.setTibtrabDesc(rs.getString("TipTrabDesc"));
                tipotrabajador.setTrabEstReg(rs.getString("TipTrabEstReg").charAt(0));
                tipoTrabajadores.add(tipotrabajador);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer el registro de trabajadores");
        }
        return tipoTrabajadores;
    }
    public void updateTrabajador(TipoTrabajador tipoTrabajador) {
        String sql = "UPDATE td_tipotrabajador SET TipTrabDesc = ?, TipTrabEstReg = ?  WHERE TrabajaTipoTraId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tipoTrabajador.getTibtrabDesc());
            pstmt.setString(2, String.valueOf(tipoTrabajador.getTrabEstReg()));
            pstmt.setString(3, String.valueOf(tipoTrabajador.getTrabajadortipoTraId()));
            pstmt.executeUpdate();
            System.out.println("Trabajador actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrabajador(Character tipoTrabajadorId) {
        String sql = "DELETE FROM td_tipotrabajador WHERE TrabajaTipoTraId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(tipoTrabajadorId));
            pstmt.executeUpdate();
            System.out.println("Trabajador eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inactivarTrabajador(Character tipoTrabajadorId) {
        String sql = "UPDATE td_tipotrabajador SET TipTrabEstReg = 'I' WHERE TrabajaTipoTraId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(tipoTrabajadorId));
            pstmt.executeUpdate();
            System.out.println("Tipo Trabajador inactivado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activarTrabajador(Character tipoTrabajadorId) {
        String sql = "UPDATE td_tipotrabajador SET TipTrabEstReg = 'A' WHERE TrabajaTipoTraId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(tipoTrabajadorId));
            pstmt.executeUpdate();
            System.out.println("Tipo Trabajador activado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
