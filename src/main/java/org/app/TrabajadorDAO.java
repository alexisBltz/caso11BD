package org.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {

    public void createTrabajador(Trabajador trabajador) {
        String sql = "INSERT INTO tb_trabajador (TrabajadorId, TrabajadorNombre, TrabajaTipoTraId, TraFecIng, TraFecCes, TraFecUltSalVac, TraEstTra, TraEstRegTra, CueCorNum, CodCenCos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, trabajador.getTrabajadorId());
            pstmt.setString(2, trabajador.getTrabajadorNombre());
            pstmt.setString(3, String.valueOf(trabajador.getTrabajaTipoTraId()));
            pstmt.setDate(4, new java.sql.Date(trabajador.getTraFecIng().getTime()));
            pstmt.setDate(5, new java.sql.Date(trabajador.getTraFecCes().getTime()));
            pstmt.setDate(6, new java.sql.Date(trabajador.getTraFecUltSalVac().getTime()));
            pstmt.setString(7, String.valueOf(trabajador.getTraEstTra()));
            pstmt.setString(8, String.valueOf(trabajador.getTraEstRegTra()));
            pstmt.setString(9, trabajador.getCueCorNum());
            pstmt.setString(10, String.valueOf(trabajador.getCodCenCos()));
            pstmt.executeUpdate();
            System.out.println("Trabajador creado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Trabajador> readTrabajadores() {
        List<Trabajador> trabajadores = new ArrayList<>();
        String sql = "SELECT * FROM tb_trabajador";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                trabajador.setTrabajadorId(rs.getInt("TrabajadorId"));
                trabajador.setTrabajadorNombre(rs.getString("TrabajadorNombre"));
                trabajador.setTrabajaTipoTraId(rs.getString("TrabajaTipoTraId").charAt(0));
                trabajador.setTraFecIng(rs.getDate("TraFecIng"));
                trabajador.setTraFecCes(rs.getDate("TraFecCes"));
                trabajador.setTraFecUltSalVac(rs.getDate("TraFecUltSalVac"));
                trabajador.setTraEstTra(rs.getString("TraEstTra").charAt(0));
                trabajador.setTraEstRegTra(rs.getString("TraEstRegTra").charAt(0));
                trabajador.setCueCorNum(rs.getString("CueCorNum"));
                trabajador.setCodCenCos(rs.getString("CodCenCos").charAt(0));
                trabajadores.add(trabajador);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al leer el registro de trabajadores");
        }
        return trabajadores;
    }

    public void updateTrabajador(Trabajador trabajador) {
        String sql = "UPDATE tb_trabajador SET TrabajadorNombre = ?, TrabajaTipoTraId = ?, TraFecIng = ?, TraFecCes = ?, TraFecUltSalVac = ?, TraEstTra = ?, TraEstRegTra = ?, CueCorNum = ?, CodCenCos = ? WHERE TrabajadorId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, trabajador.getTrabajadorNombre());
            pstmt.setString(2, String.valueOf(trabajador.getTrabajaTipoTraId()));
            pstmt.setDate(3, new java.sql.Date(trabajador.getTraFecIng().getTime()));
            pstmt.setDate(4, new java.sql.Date(trabajador.getTraFecCes().getTime()));
            pstmt.setDate(5, new java.sql.Date(trabajador.getTraFecUltSalVac().getTime()));
            pstmt.setString(6, String.valueOf(trabajador.getTraEstTra()));
            pstmt.setString(7, String.valueOf(trabajador.getTraEstRegTra()));
            pstmt.setString(8, trabajador.getCueCorNum());
            pstmt.setString(9, String.valueOf(trabajador.getCodCenCos()));
            pstmt.setInt(10, trabajador.getTrabajadorId());
            pstmt.executeUpdate();
            System.out.println("Trabajador actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrabajador(int trabajadorId) {
        String sql = "DELETE FROM tb_trabajador WHERE TrabajadorId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, trabajadorId);
            pstmt.executeUpdate();
            System.out.println("Trabajador eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inactivarTrabajador(int i) {
        String sql = "UPDATE tb_trabajador SET TraEstRegTra = 'I' WHERE TrabajadorId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, i);
            pstmt.executeUpdate();
            System.out.println("Trabajador inactivado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activarTrabajador(int i) {
        String sql = "UPDATE tb_trabajador SET TraEstRegTra = 'A' WHERE TrabajadorId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, i);
            pstmt.executeUpdate();
            System.out.println("Trabajador activado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
