package org.app.dao;

import org.app.DatabaseConnection;
import org.app.controllers.CentroCosto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CentroCostoDAO {
    public void createCentroCosto(CentroCosto centroCosto) {
        String sql = "INSERT INTO tb_centrocosto (CodCenCos, CenCosNombre, CenCostEstReg) VALUES (?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(centroCosto.getCodCenCos()));
            pstmt.setString(2, centroCosto.getCenCosNombre());
            pstmt.setString(3, String.valueOf(centroCosto.getCenCostEstReg()));
            pstmt.executeUpdate();
            System.out.println("Centro de costo creado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CentroCosto> readCentrosCosto() {
        List<CentroCosto> centrosCosto = new ArrayList<>();
        String sql = "SELECT * FROM tb_centrocosto";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CentroCosto centroCosto = new CentroCosto();
                centroCosto.setCodCenCos(rs.getString("CodCenCos").charAt(0));
                centroCosto.setCenCosNombre(rs.getString("CenCosNombre"));
                centroCosto.setCenCostEstReg(rs.getString("CenCostEstReg").charAt(0));
                centrosCosto.add(centroCosto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer el registro de centros de costo");
        }
        return centrosCosto;
    }

    public void updateCentroCosto(CentroCosto centroCosto) {
        String sql = "UPDATE tb_centrocosto SET CenCosNombre = ?, CenCostEstReg = ?  WHERE CodCenCos = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, centroCosto.getCenCosNombre());
            pstmt.setString(2, String.valueOf(centroCosto.getCenCostEstReg()));
            pstmt.setString(3, String.valueOf(centroCosto.getCodCenCos()));
            pstmt.executeUpdate();
            System.out.println("Centro de costo actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCentroCosto(Character codCenCos) {
        String sql = "DELETE FROM tb_centrocosto WHERE CodCenCos = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(codCenCos));
            pstmt.executeUpdate();
            System.out.println("Centro de costo eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inactivarCentroCosto(Character codCenCos) {
        String sql = "UPDATE tb_centrocosto SET CenCostEstReg = 'I' WHERE CodCenCos = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(codCenCos));
            pstmt.executeUpdate();
            System.out.println("Centro de costo inactivado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activarCentroCosto(Character codCenCos) {
        String sql = "UPDATE tb_centrocosto SET CenCostEstReg = 'A' WHERE CodCenCos = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(codCenCos));
            pstmt.executeUpdate();
            System.out.println("Centro de costo activado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
