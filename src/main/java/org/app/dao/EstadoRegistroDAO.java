package org.app.dao;

import org.app.DatabaseConnection;
import org.app.controllers.EstadoRegistro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstadoRegistroDAO {
    public void createEstadoRegistro(EstadoRegistro estadoRegistro) {
        String sql = "INSERT INTO tb_estreg (EstRegCod, EstRegNom, EstRegEstTra) VALUES (?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(estadoRegistro.getEstRegCod()));
            pstmt.setString(2, estadoRegistro.getEstRegNom());
            pstmt.setString(3, String.valueOf(estadoRegistro.getEstRegEstTra()));
            pstmt.executeUpdate();
            System.out.println("Estado de registro creado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EstadoRegistro> readEstadosRegistro() {
        List<EstadoRegistro> estadosRegistro = new ArrayList<>();
        String sql = "SELECT * FROM tb_estreg";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EstadoRegistro estadoRegistro = new EstadoRegistro();
                estadoRegistro.setEstRegCod(rs.getString("EstRegCod").charAt(0));
                estadoRegistro.setEstRegNom(rs.getString("EstRegNom"));
                estadoRegistro.setEstRegEstTra(rs.getString("EstRegEstTra").charAt(0));
                estadosRegistro.add(estadoRegistro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer el registro de estados de registro");
        }
        return estadosRegistro;
    }

    public void updateEstadoRegistro(EstadoRegistro estadoRegistro) {
        String sql = "UPDATE tb_estreg SET EstRegNom = ?, EstRegEstTra = ? WHERE EstRegCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, estadoRegistro.getEstRegNom());
            pstmt.setString(2, String.valueOf(estadoRegistro.getEstRegEstTra()));
            pstmt.setString(3, String.valueOf(estadoRegistro.getEstRegCod()));
            pstmt.executeUpdate();
            System.out.println("Estado de registro actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEstadoRegistro(Character estRegCod) {
        String sql = "DELETE FROM tb_estreg WHERE EstRegCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(estRegCod));
            pstmt.executeUpdate();
            System.out.println("Estado de registro eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inactivarEstadoRegistro(Character estRegCod) {
        String sql = "UPDATE tb_estreg SET EstRegEstTra = 'I' WHERE EstRegCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(estRegCod));
            pstmt.executeUpdate();
            System.out.println("Estado de registro inactivado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activarEstadoRegistro(Character estRegCod) {
        String sql = "UPDATE tb_estreg SET EstRegEstTra = 'A' WHERE EstRegCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(estRegCod));
            pstmt.executeUpdate();
            System.out.println("Estado de registro activado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
