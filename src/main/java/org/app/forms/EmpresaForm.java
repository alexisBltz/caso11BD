package org.app.forms;

import org.app.controllers.Empresa;
import org.app.dao.EmpresaDAO;
import org.app.forms.TablasReferencialCompania.TablasReferencialesEmpresa;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmpresaForm extends TablasReferencialesEmpresa {
    private EmpresaDAO empresaDAO;

    public EmpresaForm(JFrame parent) {
        super(parent);
        setTitle("Empresa");
        NombreTabla.setText("Empresa");

        empresaDAO = new EmpresaDAO();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empresa empresa = createEmpresa();

                empresaDAO.createEmpresa(empresa);
                limpiarCampos();
                cargarLista();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                limpiarCampos();
                cargarLista();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empresaDAO.deleteEmpresa(Integer.parseInt(ID.getText()));
                limpiarCampos();
                cargarLista();
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empresaDAO.inactivarEmpresa(Integer.parseInt(ID.getText()));
                EstadoRegistro.setText("I");
                cargarLista();
            }
        });

        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empresaDAO.activarEmpresa(Integer.parseInt(ID.getText()));
                EstadoRegistro.setText("A");
                cargarLista();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        results.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && results.getSelectedRow() != -1) {
                    int selectedRow = results.getSelectedRow();
                    ID.setText(results.getValueAt(selectedRow, 0).toString());
                    Descripcion.setText(results.getValueAt(selectedRow, 1).toString());
                    Direccion.setText(results.getValueAt(selectedRow, 2).toString());
                    EstadoRegistro.setText(results.getValueAt(selectedRow, 3).toString());
                    ID.setEnabled(false);
                }
            }
        });

        setVisible(true);
    }

    @Override
    protected void createUIComponents() {
        empresaDAO = new EmpresaDAO();
        List<Empresa> empresas = empresaDAO.readEmpresas();

        DefaultTableModel modelDefault = new DefaultTableModel();
        modelDefault.setColumnIdentifiers(new String[]{
                "ID", "Nombre", "Dirección", "Estado de Registro"
        });

        for (Empresa empresa : empresas) {
            Object[] rowData = {
                    empresa.getEmprCod(),
                    empresa.getEmprNom(),
                    empresa.getEmprDir(),
                    empresa.getEmprEstRegEmpCod(),
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);
    }

    private void update() {
        Empresa empresa = createEmpresa();
        empresaDAO.updateEmpresa(empresa);
        limpiarCampos();
        cargarLista();
    }

    private void cargarLista() {
        List<Empresa> lista = empresaDAO.readEmpresas();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for (Empresa empresa : lista) {
            Object[] data = {
                    empresa.getEmprCod(),
                    empresa.getEmprNom(),
                    empresa.getEmprDir(),
                    empresa.getEmprEstRegEmpCod(),
            };
            modelo.addRow(data);
        }
    }

    private Empresa createEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setEmprCod(Integer.parseInt(ID.getText()));
        empresa.setEmprNom(Descripcion.getText());
        empresa.setEmprDir(Direccion.getText());
        empresa.setEmprEstRegEmpCod(EstadoRegistro.getText().charAt(0));
        return empresa;
    }

    private void limpiarCampos() {
        ID.setText("");
        Descripcion.setText("");
        Direccion.setText("");
        EstadoRegistro.setText("");
    }

    public static void main(String[] args) {
        EmpresaForm empresaForm = new EmpresaForm(null);
    }
}
