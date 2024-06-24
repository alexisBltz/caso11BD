package org.app.dao;

import org.app.DatabaseConnection;
import org.app.controllers.TipoDescuento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoDescuentoDAO {
    public void createTipoDescuento(TipoDescuento tipoDescuento) {
        String sql = "INSERT INTO tb_tipodescuento (DescuPlaCod, TiPdesPlaNom, TiPdesPlaEstReg) VALUES (?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, tipoDescuento.getDescuPlaCod());
            pstmt.setString(2, tipoDescuento.getTiPdesPlaNom());
            pstmt.setString(3, String.valueOf(tipoDescuento.getTiPdesPlaEstReg()));
            pstmt.executeUpdate();
            System.out.println("Tipo de descuento creado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TipoDescuento> readTiposDescuento() {
        List<TipoDescuento> tiposDescuento = new ArrayList<>();
        String sql = "SELECT * FROM tb_tipodescuento";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoDescuento tipoDescuento = new TipoDescuento();
                tipoDescuento.setDescuPlaCod(rs.getInt("DescuPlaCod"));
                tipoDescuento.setTiPdesPlaNom(rs.getString("TiPdesPlaNom"));
                tipoDescuento.setTiPdesPlaEstReg(rs.getString("TiPdesPlaEstReg").charAt(0));
                tiposDescuento.add(tipoDescuento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer el registro de tipos de descuento");
        }
        return tiposDescuento;
    }

    public void updateTipoDescuento(TipoDescuento tipoDescuento) {
        String sql = "UPDATE tb_tipodescuento SET TiPdesPlaNom = ?, TiPdesPlaEstReg = ?  WHERE DescuPlaCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tipoDescuento.getTiPdesPlaNom());
            pstmt.setString(2, String.valueOf(tipoDescuento.getTiPdesPlaEstReg()));
            pstmt.setInt(3, tipoDescuento.getDescuPlaCod());
            pstmt.executeUpdate();
            System.out.println("Tipo de descuento actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTipoDescuento(int descuPlaCod) {
        String sql = "DELETE FROM tb_tipodescuento WHERE DescuPlaCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, descuPlaCod);
            pstmt.executeUpdate();
            System.out.println("Tipo de descuento eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inactivarTipoDescuento(int descuPlaCod) {
        String sql = "UPDATE tb_tipodescuento SET TiPdesPlaEstReg = 'I' WHERE DescuPlaCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, descuPlaCod);
            pstmt.executeUpdate();
            System.out.println("Tipo de descuento inactivado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activarTipoDescuento(int descuPlaCod) {
        String sql = "UPDATE tb_tipodescuento SET TiPdesPlaEstReg = 'A' WHERE DescuPlaCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, descuPlaCod);
            pstmt.executeUpdate();
            System.out.println("Tipo de descuento activado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
