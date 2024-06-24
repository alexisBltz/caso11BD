package org.app.forms;

import org.app.controllers.EstadoTrabajador;
import org.app.dao.EstadoTrabajadorDAO;
import org.app.forms.ModelosFormularios.TablasReferencialesBase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EstadoTrabajadorForm extends TablasReferencialesBase {
    private EstadoTrabajadorDAO estadoTrabajadorDAO;

    public EstadoTrabajadorForm(JFrame parent) {
        super(parent);
        setTitle("Estado de Trabajador");
        NombreTabla.setText("Estado de Trabajador");

        estadoTrabajadorDAO = new EstadoTrabajadorDAO();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoTrabajador estadoTrabajador = createEstadoTrabajador();

                estadoTrabajadorDAO.createEstadoTrabajador(estadoTrabajador);
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
                estadoTrabajadorDAO.deleteEstadoTrabajador(ID.getText().charAt(0));
                limpiarCampos();
                cargarLista();
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoTrabajadorDAO.inactivarEstadoTrabajador(ID.getText().charAt(0));
                EstadoRegistro.setText("I");
                cargarLista();
            }
        });

        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoTrabajadorDAO.activarEstadoTrabajador(ID.getText().charAt(0));
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
        estadoTrabajadorDAO = new EstadoTrabajadorDAO();
        List<EstadoTrabajador> estadosTrabajador = estadoTrabajadorDAO.readEstadosTrabajador();

        DefaultTableModel modelDefault = new DefaultTableModel();
        modelDefault.setColumnIdentifiers(new String[] {
                "ID", "Nombre", "Estado de Registro"
        });

        for (EstadoTrabajador estadoTrabajador : estadosTrabajador) {
            Object[] rowData = {
                    estadoTrabajador.getEstadoTraId(),
                    estadoTrabajador.getEstadoTraNom(),
                    estadoTrabajador.getEstadoEstReg(),
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);
    }

    private void update() {
        EstadoTrabajador estadoTrabajador = createEstadoTrabajador();
        estadoTrabajadorDAO.updateEstadoTrabajador(estadoTrabajador);
        limpiarCampos();
        cargarLista();
    }

    private void cargarLista() {
        List<EstadoTrabajador> lista = estadoTrabajadorDAO.readEstadosTrabajador();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for (EstadoTrabajador estadoTrabajador : lista) {
            Object[] data = {
                    estadoTrabajador.getEstadoTraId(),
                    estadoTrabajador.getEstadoTraNom(),
                    estadoTrabajador.getEstadoEstReg(),
            };
            modelo.addRow(data);
        }
    }

    private EstadoTrabajador createEstadoTrabajador() {
        EstadoTrabajador estadoTrabajador = new EstadoTrabajador();
        estadoTrabajador.setEstadoTraId(ID.getText().charAt(0));
        estadoTrabajador.setEstadoTraNom(Descripcion.getText());
        estadoTrabajador.setEstadoEstReg(EstadoRegistro.getText().charAt(0));
        return estadoTrabajador;
    }

    private void limpiarCampos() {
        ID.setText("");
        Descripcion.setText("");
        EstadoRegistro.setText("");
    }

    public static void main(String[] args) {
        EstadoTrabajadorForm estadoTrabajadorForm = new EstadoTrabajadorForm(null);
    }
}
