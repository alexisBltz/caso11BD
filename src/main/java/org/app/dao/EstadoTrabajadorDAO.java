package org.app.dao;

import org.app.DatabaseConnection;
import org.app.controllers.EstadoTrabajador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoTrabajadorDAO {
    public void createEstadoTrabajador(EstadoTrabajador estadoTrabajador) {
        String sql = "INSERT INTO td_estadotrabajador (EstadoTraId, EstadoTraNom, EstadoEstReg) VALUES (?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(estadoTrabajador.getEstadoTraId()));
            pstmt.setString(2, estadoTrabajador.getEstadoTraNom());
            pstmt.setString(3, String.valueOf(estadoTrabajador.getEstadoEstReg()));
            pstmt.executeUpdate();
            System.out.println("Estado de trabajador creado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EstadoTrabajador> readEstadosTrabajador() {
        List<EstadoTrabajador> estadosTrabajador = new ArrayList<>();
        String sql = "SELECT * FROM td_estadotrabajador";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EstadoTrabajador estadoTrabajador = new EstadoTrabajador();
                estadoTrabajador.setEstadoTraId(rs.getString("EstadoTraId").charAt(0));
                estadoTrabajador.setEstadoTraNom(rs.getString("EstadoTraNom"));
                estadoTrabajador.setEstadoEstReg(rs.getString("EstadoEstReg").charAt(0));
                estadosTrabajador.add(estadoTrabajador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer el registro de estados de trabajador");
        }
        return estadosTrabajador;
    }

    public void updateEstadoTrabajador(EstadoTrabajador estadoTrabajador) {
        String sql = "UPDATE td_estadotrabajador SET EstadoTraNom = ?, EstadoEstReg = ?  WHERE EstadoTraId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, estadoTrabajador.getEstadoTraNom());
            pstmt.setString(2, String.valueOf(estadoTrabajador.getEstadoEstReg()));
            pstmt.setString(3, String.valueOf(estadoTrabajador.getEstadoTraId()));
            pstmt.executeUpdate();
            System.out.println("Estado de trabajador actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEstadoTrabajador(Character estadoTraId) {
        String sql = "DELETE FROM td_estadotrabajador WHERE EstadoTraId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(estadoTraId));
            pstmt.executeUpdate();
            System.out.println("Estado de trabajador eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inactivarEstadoTrabajador(Character estadoTraId) {
        String sql = "UPDATE td_estadotrabajador SET EstadoEstReg = 'I' WHERE EstadoTraId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(estadoTraId));
            pstmt.executeUpdate();
            System.out.println("Estado de trabajador inactivado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activarEstadoTrabajador(Character estadoTraId) {
        String sql = "UPDATE td_estadotrabajador SET EstadoEstReg = 'A' WHERE EstadoTraId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(estadoTraId));
            pstmt.executeUpdate();
            System.out.println("Estado de trabajador activado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
