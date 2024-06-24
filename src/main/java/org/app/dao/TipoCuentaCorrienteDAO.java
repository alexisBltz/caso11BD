package org.app.dao;

import org.app.DatabaseConnection;
import org.app.controllers.TipoCuentaCorriente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoCuentaCorrienteDAO {
    public void createTipoCuentaCorriente(TipoCuentaCorriente tipoCuentaCorriente) {
        String sql = "INSERT INTO tb_tipocuentacorriente (TipoCuentaCorrienteCod, TipCueCorNom, TipCueCorEstReg) VALUES (?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, tipoCuentaCorriente.getTipoCuentaCorrienteCod());
            pstmt.setString(2, tipoCuentaCorriente.getTipCueCorNom());
            pstmt.setString(3, String.valueOf(tipoCuentaCorriente.getTipCueCorEstReg()));
            pstmt.executeUpdate();
            System.out.println("Tipo de cuenta corriente creado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TipoCuentaCorriente> readTiposCuentaCorriente() {
        List<TipoCuentaCorriente> tiposCuentaCorriente = new ArrayList<>();
        String sql = "SELECT * FROM tb_tipocuentacorriente";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoCuentaCorriente tipoCuentaCorriente = new TipoCuentaCorriente();
                tipoCuentaCorriente.setTipoCuentaCorrienteCod(rs.getInt("TipoCuentaCorrienteCod"));
                tipoCuentaCorriente.setTipCueCorNom(rs.getString("TipCueCorNom"));
                tipoCuentaCorriente.setTipCueCorEstReg(rs.getString("TipCueCorEstReg").charAt(0));
                tiposCuentaCorriente.add(tipoCuentaCorriente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer el registro de tipos de cuenta corriente");
        }
        return tiposCuentaCorriente;
    }

    public void updateTipoCuentaCorriente(TipoCuentaCorriente tipoCuentaCorriente) {
        String sql = "UPDATE tb_tipocuentacorriente SET TipCueCorNom = ?, TipCueCorEstReg = ?  WHERE TipoCuentaCorrienteCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tipoCuentaCorriente.getTipCueCorNom());
            pstmt.setString(2, String.valueOf(tipoCuentaCorriente.getTipCueCorEstReg()));
            pstmt.setInt(3, tipoCuentaCorriente.getTipoCuentaCorrienteCod());
            pstmt.executeUpdate();
            System.out.println("Tipo de cuenta corriente actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTipoCuentaCorriente(int tipoCuentaCorrienteCod) {
        String sql = "DELETE FROM tb_tipocuentacorriente WHERE TipoCuentaCorrienteCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, tipoCuentaCorrienteCod);
            pstmt.executeUpdate();
            System.out.println("Tipo de cuenta corriente eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inactivarTipoCuentaCorriente(int tipoCuentaCorrienteCod) {
        String sql = "UPDATE tb_tipocuentacorriente SET TipCueCorEstReg = 'I' WHERE TipoCuentaCorrienteCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, tipoCuentaCorrienteCod);
            pstmt.executeUpdate();
            System.out.println("Tipo de cuenta corriente inactivado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activarTipoCuentaCorriente(int tipoCuentaCorrienteCod) {
        String sql = "UPDATE tb_tipocuentacorriente SET TipCueCorEstReg = 'A' WHERE TipoCuentaCorrienteCod = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, tipoCuentaCorrienteCod);
            pstmt.executeUpdate();
            System.out.println("Tipo de cuenta corriente activado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
