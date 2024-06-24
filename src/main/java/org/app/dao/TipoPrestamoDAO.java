package org.app.dao;

import org.app.DatabaseConnection;
import org.app.controllers.TipoPrestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoPrestamoDAO {
    public void createTipoPrestamo(TipoPrestamo tipoPrestamo) {
        String sql = "INSERT INTO tb_tipoprestamo (TipoPrestamoId, TipPresNom, TipPresEstReg) VALUES (?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(tipoPrestamo.getTipoPrestamoId()));
            pstmt.setString(2, tipoPrestamo.getTipPresNom());
            pstmt.setString(3, String.valueOf(tipoPrestamo.getTipPresEstReg()));
            pstmt.executeUpdate();
            System.out.println("Tipo de préstamo creado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TipoPrestamo> readTiposPrestamo() {
        List<TipoPrestamo> tiposPrestamo = new ArrayList<>();
        String sql = "SELECT * FROM tb_tipoprestamo";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoPrestamo tipoPrestamo = new TipoPrestamo();
                tipoPrestamo.setTipoPrestamoId(rs.getString("TipoPrestamoId").charAt(0));
                tipoPrestamo.setTipPresNom(rs.getString("TipPresNom"));
                tipoPrestamo.setTipPresEstReg(rs.getString("TipPresEstReg").charAt(0));
                tiposPrestamo.add(tipoPrestamo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer el registro de tipos de préstamo");
        }
        return tiposPrestamo;
    }

    public void updateTipoPrestamo(TipoPrestamo tipoPrestamo) {
        String sql = "UPDATE tb_tipoprestamo SET TipPresNom = ?, TipPresEstReg = ?  WHERE TipoPrestamoId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tipoPrestamo.getTipPresNom());
            pstmt.setString(2, String.valueOf(tipoPrestamo.getTipPresEstReg()));
            pstmt.setString(3, String.valueOf(tipoPrestamo.getTipoPrestamoId()));
            pstmt.executeUpdate();
            System.out.println("Tipo de préstamo actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTipoPrestamo(Character tipoPrestamoId) {
        String sql = "DELETE FROM tb_tipoprestamo WHERE TipoPrestamoId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(tipoPrestamoId));
            pstmt.executeUpdate();
            System.out.println("Tipo de préstamo eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inactivarTipoPrestamo(Character tipoPrestamoId) {
        String sql = "UPDATE tb_tipoprestamo SET TipPresEstReg = 'I' WHERE TipoPrestamoId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(tipoPrestamoId));
            pstmt.executeUpdate();
            System.out.println("Tipo de préstamo inactivado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activarTipoPrestamo(Character tipoPrestamoId) {
        String sql = "UPDATE tb_tipoprestamo SET TipPresEstReg = 'A' WHERE TipoPrestamoId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(tipoPrestamoId));
            pstmt.executeUpdate();
            System.out.println("Tipo de préstamo activado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
