package org.app.forms;

import org.app.controllers.TipoDescuento;
import org.app.dao.TipoDescuentoDAO;
import org.app.forms.ModelosFormularios.TablasReferencialesBase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TipoDescuentoForm extends TablasReferencialesBase {
    private TipoDescuentoDAO tipoDescuentoDAO;

    public TipoDescuentoForm(JFrame parent) {
        super(parent);
        setTitle("Tipo de Descuento");
        NombreTabla.setText("Tipo de Descuento");

        tipoDescuentoDAO = new TipoDescuentoDAO();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoDescuento tipoDescuento = createTipoDescuento();

                tipoDescuentoDAO.createTipoDescuento(tipoDescuento);
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
                tipoDescuentoDAO.deleteTipoDescuento(Integer.parseInt(ID.getText()));
                limpiarCampos();
                cargarLista();
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipoDescuentoDAO.inactivarTipoDescuento(Integer.parseInt(ID.getText()));
                EstadoRegistro.setText("I");
                cargarLista();
            }
        });

        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipoDescuentoDAO.activarTipoDescuento(Integer.parseInt(ID.getText()));
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
        tipoDescuentoDAO = new TipoDescuentoDAO();
        List<TipoDescuento> tiposDescuento = tipoDescuentoDAO.readTiposDescuento();

        DefaultTableModel modelDefault = new DefaultTableModel();
        modelDefault.setColumnIdentifiers(new String[] {
                "ID", "Nombre", "Estado de Registro"
        });

        for (TipoDescuento tipoDescuento : tiposDescuento) {
            Object[] rowData = {
                    tipoDescuento.getDescuPlaCod(),
                    tipoDescuento.getTiPdesPlaNom(),
                    tipoDescuento.getTiPdesPlaEstReg(),
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);
    }

    private void update() {
        TipoDescuento tipoDescuento = createTipoDescuento();
        tipoDescuentoDAO.updateTipoDescuento(tipoDescuento);
        limpiarCampos();
        cargarLista();
    }

    private void cargarLista() {
        List<TipoDescuento> lista = tipoDescuentoDAO.readTiposDescuento();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for (TipoDescuento tipoDescuento : lista) {
            Object[] data = {
                    tipoDescuento.getDescuPlaCod(),
                    tipoDescuento.getTiPdesPlaNom(),
                    tipoDescuento.getTiPdesPlaEstReg(),
            };
            modelo.addRow(data);
        }
    }

    private TipoDescuento createTipoDescuento() {
        TipoDescuento tipoDescuento = new TipoDescuento();
        tipoDescuento.setDescuPlaCod(Integer.parseInt(ID.getText()));
        tipoDescuento.setTiPdesPlaNom(Descripcion.getText());
        tipoDescuento.setTiPdesPlaEstReg(EstadoRegistro.getText().charAt(0));
        return tipoDescuento;
    }

    private void limpiarCampos() {
        ID.setText("");
        Descripcion.setText("");
        EstadoRegistro.setText("");
    }

    public static void main(String[] args) {
        TipoDescuentoForm tipoDescuentoForm = new TipoDescuentoForm(null);
    }
}
