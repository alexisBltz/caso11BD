package org.app.dao;

import org.app.DatabaseConnection;
import org.app.controllers.Compania;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompaniaDAO {

    // Crear compañía
    public void createCompania(Compania compania) {
        String sql = "INSERT INTO tb_compania (ComCod, ComNom, ComEmprCod, ComDir, ComEstRegComCod) VALUES (?,?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, compania.getComCod());
            pstmt.setString(2, compania.getComNom());
            pstmt.setInt(3, compania.getComEmprCod());
            pstmt.setString(4, compania.getComDir());
            pstmt.setString(5, String.valueOf(compania.getComEstRegComCod()));
            pstmt.executeUpdate();
            System.out.println("Compania creada exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer todas las compañías
    public List<Compania> readCompanias() {
        List<Compania> companias = new ArrayList<>();
        String sql = "SELECT * FROM tb_compania";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Compania compania = new Compania();
                compania.setComCod(rs.getInt("ComCod"));
                compania.setComNom(rs.getString("ComNom"));
                compania.setComEmprCod(rs.getInt("ComEmprCod"));
                compania.setComDir(rs.getString("ComDir"));
                compania.setComEstRegComCod(rs.getString("ComEstRegComCod").charAt(0));
                companias.add(compania);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer las compañías");
        }
        return companias;
    }

    // Actualizar compañía
    public void updateCompania(Compania compania) {
        String sql = "UPDATE tb_compania SET ComNom = ?, ComEmprCod = ?, ComDir = ?, ComEstRegComCod = ? WHERE ComCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, compania.getComNom());
            pstmt.setInt(2, compania.getComEmprCod());
            pstmt.setString(3, compania.getComDir());
            pstmt.setString(4, String.valueOf(compania.getComEstRegComCod()));
            pstmt.setInt(5, compania.getComCod());
            pstmt.executeUpdate();
            System.out.println("Compania actualizada exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar compañía
    public void deleteCompania(int comCod) {
        String sql = "DELETE FROM tb_compania WHERE ComCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, comCod);
            pstmt.executeUpdate();
            System.out.println("Compania eliminada exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Inactivar compañía
    public void inactivarCompania(int comCod) {
        String sql = "UPDATE tb_compania SET ComEstRegComCod = 'I' WHERE ComCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, comCod);
            pstmt.executeUpdate();
            System.out.println("Compania inactivada exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Activar compañía
    public void activarCompania(int comCod) {
        String sql = "UPDATE tb_compania SET ComEstRegComCod = 'A' WHERE ComCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, comCod);
            pstmt.executeUpdate();
            System.out.println("Compania activada exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
