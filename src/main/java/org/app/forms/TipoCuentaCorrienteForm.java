package org.app.forms;

import org.app.controllers.TipoCuentaCorriente;
import org.app.dao.TipoCuentaCorrienteDAO;
import org.app.forms.ModelosFormularios.TablasReferencialesBase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TipoCuentaCorrienteForm extends TablasReferencialesBase {
    private TipoCuentaCorrienteDAO tipoCuentaCorrienteDAO;

    public TipoCuentaCorrienteForm(JFrame parent) {
        super(parent);
        setTitle("Tipo de Cuenta Corriente");
        NombreTabla.setText("Tipo de Cuenta Corriente");

        tipoCuentaCorrienteDAO = new TipoCuentaCorrienteDAO();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoCuentaCorriente tipoCuentaCorriente = createTipoCuentaCorriente();

                tipoCuentaCorrienteDAO.createTipoCuentaCorriente(tipoCuentaCorriente);
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
                tipoCuentaCorrienteDAO.deleteTipoCuentaCorriente(Integer.parseInt(ID.getText()));
                limpiarCampos();
                cargarLista();
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipoCuentaCorrienteDAO.inactivarTipoCuentaCorriente(Integer.parseInt(ID.getText()));
                EstadoRegistro.setText("I");
                cargarLista();
            }
        });

        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipoCuentaCorrienteDAO.activarTipoCuentaCorriente(Integer.parseInt(ID.getText()));
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
        tipoCuentaCorrienteDAO = new TipoCuentaCorrienteDAO();
        List<TipoCuentaCorriente> tiposCuentaCorriente = tipoCuentaCorrienteDAO.readTiposCuentaCorriente();

        DefaultTableModel modelDefault = new DefaultTableModel();
        modelDefault.setColumnIdentifiers(new String[]{
                "ID", "Nombre", "Estado de Registro"
        });

        for (TipoCuentaCorriente tipoCuentaCorriente : tiposCuentaCorriente) {
            Object[] rowData = {
                    tipoCuentaCorriente.getTipoCuentaCorrienteCod(),
                    tipoCuentaCorriente.getTipCueCorNom(),
                    tipoCuentaCorriente.getTipCueCorEstReg(),
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);
    }

    private void update() {
        TipoCuentaCorriente tipoCuentaCorriente = createTipoCuentaCorriente();
        tipoCuentaCorrienteDAO.updateTipoCuentaCorriente(tipoCuentaCorriente);
        limpiarCampos();
        cargarLista();
    }

    private void cargarLista() {
        List<TipoCuentaCorriente> lista = tipoCuentaCorrienteDAO.readTiposCuentaCorriente();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for (TipoCuentaCorriente tipoCuentaCorriente : lista) {
            Object[] data = {
                    tipoCuentaCorriente.getTipoCuentaCorrienteCod(),
                    tipoCuentaCorriente.getTipCueCorNom(),
                    tipoCuentaCorriente.getTipCueCorEstReg(),
            };
            modelo.addRow(data);
        }
    }

    private TipoCuentaCorriente createTipoCuentaCorriente() {
        TipoCuentaCorriente tipoCuentaCorriente = new TipoCuentaCorriente();
        tipoCuentaCorriente.setTipoCuentaCorrienteCod(Integer.parseInt(ID.getText()));
        tipoCuentaCorriente.setTipCueCorNom(Descripcion.getText());
        tipoCuentaCorriente.setTipCueCorEstReg(EstadoRegistro.getText().charAt(0));
        return tipoCuentaCorriente;
    }

    private void limpiarCampos() {
        ID.setText("");
        Descripcion.setText("");
        EstadoRegistro.setText("");
    }

    public static void main(String[] args) {
        TipoCuentaCorrienteForm tipoCuentaCorrienteForm = new TipoCuentaCorrienteForm(null);
    }
}

