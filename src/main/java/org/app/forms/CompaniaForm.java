package org.app.forms;

import org.app.controllers.Compania;
import org.app.dao.CompaniaDAO;
import org.app.forms.ModelosFormularios.TablasReferencialesCompani;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CompaniaForm extends TablasReferencialesCompani {
    private CompaniaDAO companiaDAO;

    public CompaniaForm(JFrame parent) {
        super(parent);
        setTitle("Compañía");
        NombreTabla.setText("Compañía");

        companiaDAO = new CompaniaDAO();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Compania compania = createCompania();

                companiaDAO.createCompania(compania);
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
                companiaDAO.deleteCompania(Integer.parseInt(ID.getText()));
                limpiarCampos();
                cargarLista();
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                companiaDAO.inactivarCompania(Integer.parseInt(ID.getText()));
                EstadoRegistro.setText("I");
                cargarLista();
            }
        });

        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                companiaDAO.activarCompania(Integer.parseInt(ID.getText()));
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
                    EmpresaCodigo.setText(results.getValueAt(selectedRow, 2).toString());
                    Direccion.setText(results.getValueAt(selectedRow, 3).toString());
                    EstadoRegistro.setText(results.getValueAt(selectedRow, 4).toString());
                    ID.setEnabled(false);
                }
            }
        });

        setVisible(true);
    }

    @Override
    protected void createUIComponents() {
        companiaDAO = new CompaniaDAO();
        List<Compania> companias = companiaDAO.readCompanias();

        DefaultTableModel modelDefault = new DefaultTableModel();
        modelDefault.setColumnIdentifiers(new String[]{
                "ID", "Nombre", "Código Empresa", "Dirección", "Estado de Registro"
        });

        for (Compania compania : companias) {
            Object[] rowData = {
                    compania.getComCod(),
                    compania.getComNom(),
                    compania.getComEmprCod(),
                    compania.getComDir(),
                    compania.getComEstRegComCod(),
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);
    }

    private void update() {
        Compania compania = createCompania();
        companiaDAO.updateCompania(compania);
        limpiarCampos();
        cargarLista();
    }

    private void cargarLista() {
        List<Compania> lista = companiaDAO.readCompanias();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for (Compania compania : lista) {
            Object[] data = {
                    compania.getComCod(),
                    compania.getComNom(),
                    compania.getComEmprCod(),
                    compania.getComDir(),
                    compania.getComEstRegComCod(),
            };
            modelo.addRow(data);
        }
    }

    private Compania createCompania() {
        Compania compania = new Compania();
        compania.setComCod(Integer.parseInt(ID.getText()));
        compania.setComNom(Descripcion.getText());
        compania.setComEmprCod(Integer.parseInt(EmpresaCodigo.getText()));
        compania.setComDir(Direccion.getText());
        compania.setComEstRegComCod(EstadoRegistro.getText().charAt(0));
        return compania;
    }

    private void limpiarCampos() {
        ID.setText("");
        Descripcion.setText("");
        EmpresaCodigo.setText("");
        Direccion.setText("");
        EstadoRegistro.setText("");
    }

    public static void main(String[] args) {
        CompaniaForm companiaForm = new CompaniaForm(null);
    }
}
