package org.app.forms;

import org.app.controllers.TipoPrestamo;
import org.app.dao.TipoPrestamoDAO;
import org.app.forms.ModelosFormularios.TablasReferencialesBase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TipoPrestamoForm extends TablasReferencialesBase {
    private TipoPrestamoDAO tipoPrestamoDAO;

    public TipoPrestamoForm(JFrame parent) {
        super(parent);
        setTitle("Tipo de Préstamo");
        NombreTabla.setText("Tipo de Préstamo");

        tipoPrestamoDAO = new TipoPrestamoDAO();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoPrestamo tipoPrestamo = createTipoPrestamo();

                tipoPrestamoDAO.createTipoPrestamo(tipoPrestamo);
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
                tipoPrestamoDAO.deleteTipoPrestamo(ID.getText().charAt(0));
                limpiarCampos();
                cargarLista();
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipoPrestamoDAO.inactivarTipoPrestamo(EstadoRegistro.getText().charAt(0));
                EstadoRegistro.setText("I");
                cargarLista();
            }
        });

        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipoPrestamoDAO.activarTipoPrestamo(EstadoRegistro.getText().charAt(0));
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
        tipoPrestamoDAO = new TipoPrestamoDAO();
        List<TipoPrestamo> tiposPrestamo = tipoPrestamoDAO.readTiposPrestamo();

        DefaultTableModel modelDefault = new DefaultTableModel();
        modelDefault.setColumnIdentifiers(new String[] {
                "ID", "Nombre", "Estado de Registro"
        });

        for (TipoPrestamo tipoPrestamo : tiposPrestamo) {
            Object[] rowData = {
                    tipoPrestamo.getTipoPrestamoId(),
                    tipoPrestamo.getTipPresNom(),
                    tipoPrestamo.getTipPresEstReg(),
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);
    }

    private void update() {
        TipoPrestamo tipoPrestamo = createTipoPrestamo();
        tipoPrestamoDAO.updateTipoPrestamo(tipoPrestamo);
        limpiarCampos();
        cargarLista();
    }

    private void cargarLista() {
        List<TipoPrestamo> lista = tipoPrestamoDAO.readTiposPrestamo();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for (TipoPrestamo tipoPrestamo : lista) {
            Object[] data = {
                    tipoPrestamo.getTipoPrestamoId(),
                    tipoPrestamo.getTipPresNom(),
                    tipoPrestamo.getTipPresEstReg(),
            };
            modelo.addRow(data);
        }
    }

    private TipoPrestamo createTipoPrestamo() {
        TipoPrestamo tipoPrestamo = new TipoPrestamo();
        tipoPrestamo.setTipoPrestamoId(ID.getText().charAt(0));
        tipoPrestamo.setTipPresNom(Descripcion.getText());
        tipoPrestamo.setTipPresEstReg(EstadoRegistro.getText().charAt(0));
        return tipoPrestamo;
    }

    private void limpiarCampos() {
        ID.setText("");
        Descripcion.setText("");
        EstadoRegistro.setText("");
    }

    public static void main(String[] args) {
        TipoPrestamoForm tipoPrestamoForm = new TipoPrestamoForm(null);
    }
}
