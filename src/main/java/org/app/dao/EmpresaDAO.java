package org.app.dao;

import org.app.controllers.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {
    private final String url = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private final String user = "tu_usuario";
    private final String password = "tu_contraseña";

    public void createEmpresa(Empresa empresa) {
        String sql = "INSERT INTO tb_empresa (emprCod, emprNom, emprDir, emprEstRegEmpCod) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empresa.getEmprCod());
            pstmt.setString(2, empresa.getEmprNom());
            pstmt.setString(3, empresa.getEmprDir());
            pstmt.setString(4, String.valueOf(empresa.getEmprEstRegEmpCod()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Empresa> readEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM tb_empresa";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setEmprCod(rs.getInt("emprCod"));
                empresa.setEmprNom(rs.getString("emprNom"));
                empresa.setEmprDir(rs.getString("emprDir"));
                empresa.setEmprEstRegEmpCod(rs.getString("emprEstRegEmpCod").charAt(0));
                empresas.add(empresa);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return empresas;
    }

    public void updateEmpresa(Empresa empresa) {
        String sql = "UPDATE tb_empresa SET emprNom = ?, emprDir = ?, emprEstRegEmpCod = ? WHERE emprCod = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empresa.getEmprNom());
            pstmt.setString(2, empresa.getEmprDir());
            pstmt.setString(3, String.valueOf(empresa.getEmprEstRegEmpCod()));
            pstmt.setInt(4, empresa.getEmprCod());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmpresa(int emprCod) {
        String sql = "DELETE FROM tb_empresa WHERE emprCod = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, emprCod);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inactivarEmpresa(int emprCod) {
        String sql = "UPDATE tb_empresa SET emprEstRegEmpCod = 'I' WHERE emprCod = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, emprCod);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void activarEmpresa(int emprCod) {
        String sql = "UPDATE tb_empresa SET emprEstRegEmpCod = 'A' WHERE emprCod = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, emprCod);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
