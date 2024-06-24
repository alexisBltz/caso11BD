package org.app.forms;

import org.app.controllers.EstadoRegistro;
import org.app.dao.EstadoRegistroDAO;
import org.app.forms.ModelosFormularios.TablasReferencialesBase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EstadoRegistroForm extends TablasReferencialesBase {
    private EstadoRegistroDAO estadoRegistroDAO;

    public EstadoRegistroForm(JFrame parent) {
        super(parent);
        setTitle("Estado de Registro");
        NombreTabla.setText("Estado de Registro");

        estadoRegistroDAO = new EstadoRegistroDAO();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoRegistro estadoRegistro = createEstadoRegistro();

                estadoRegistroDAO.createEstadoRegistro(estadoRegistro);
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
                estadoRegistroDAO.deleteEstadoRegistro(ID.getText().charAt(0));
                limpiarCampos();
                cargarLista();
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoRegistroDAO.inactivarEstadoRegistro(ID.getText().charAt(0));
                EstadoRegistro.setText("I");
                cargarLista();
            }
        });

        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoRegistroDAO.activarEstadoRegistro(ID.getText().charAt(0));
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
                    EstadoRegistro.setText(results.getValueAt(selectedRow, 2).toString());
                    ID.setEnabled(false);
                }
            }
        });

        setVisible(true);
    }

    @Override
    protected void createUIComponents() {
        estadoRegistroDAO = new EstadoRegistroDAO();
        List<EstadoRegistro> estadosRegistro = estadoRegistroDAO.readEstadosRegistro();

        DefaultTableModel modelDefault = new DefaultTableModel();
        modelDefault.setColumnIdentifiers(new String[] {
                "ID", "Nombre", "Estado de Registro"
        });

        for (EstadoRegistro estadoRegistro : estadosRegistro) {
            Object[] rowData = {
                    estadoRegistro.getEstRegCod(),
                    estadoRegistro.getEstRegNom(),
                    estadoRegistro.getEstRegEstTra(),
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);
    }

    private void update() {
        EstadoRegistro estadoRegistro = createEstadoRegistro();
        estadoRegistroDAO.updateEstadoRegistro(estadoRegistro);
        limpiarCampos();
        cargarLista();
    }

    private void cargarLista() {
        List<EstadoRegistro> lista = estadoRegistroDAO.readEstadosRegistro();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for (EstadoRegistro estadoRegistro : lista) {
            Object[] data = {
                    estadoRegistro.getEstRegCod(),
                    estadoRegistro.getEstRegNom(),
                    estadoRegistro.getEstRegEstTra(),
            };
            modelo.addRow(data);
        }
    }

    private EstadoRegistro createEstadoRegistro() {
        EstadoRegistro estadoRegistro = new EstadoRegistro();
        estadoRegistro.setEstRegCod(ID.getText().charAt(0));
        estadoRegistro.setEstRegNom(Descripcion.getText());
        estadoRegistro.setEstRegEstTra(EstadoRegistro.getText().charAt(0));
        return estadoRegistro;
    }

    private void limpiarCampos() {
        ID.setText("");
        Descripcion.setText("");
        EstadoRegistro.setText("");
    }

    public static void main(String[] args) {
        EstadoRegistroForm estadoRegistroForm = new EstadoRegistroForm(null);
    }
}
