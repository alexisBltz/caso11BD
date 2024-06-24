package org.app.forms;

import org.app.controllers.CentroCosto;
import org.app.dao.CentroCostoDAO;
import org.app.forms.ModelosFormularios.TablasReferencialesBase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CentroCostoForm extends TablasReferencialesBase {
    private CentroCostoDAO centroCostoDAO;

    public CentroCostoForm(JFrame parent) {
        super(parent);
        setTitle("Centro de Costo");
        NombreTabla.setText("Centro de Costo");

        centroCostoDAO = new CentroCostoDAO();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CentroCosto centroCosto = createCentroCosto();

                centroCostoDAO.createCentroCosto(centroCosto);
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
                centroCostoDAO.deleteCentroCosto(ID.getText().charAt(0));
                limpiarCampos();
                cargarLista();
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centroCostoDAO.inactivarCentroCosto(EstadoRegistro.getText().charAt(0));
                EstadoRegistro.setText("I");
                cargarLista();
            }
        });

        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centroCostoDAO.activarCentroCosto(EstadoRegistro.getText().charAt(0));
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
        centroCostoDAO = new CentroCostoDAO();
        List<CentroCosto> centrosCosto = centroCostoDAO.readCentrosCosto();

        DefaultTableModel modelDefault = new DefaultTableModel();
        modelDefault.setColumnIdentifiers(new String[] {
                "ID", "Nombre", "Estado de Registro"
        });

        for (CentroCosto centroCosto : centrosCosto) {
            Object[] rowData = {
                    centroCosto.getCodCenCos(),
                    centroCosto.getCenCosNombre(),
                    centroCosto.getCenCostEstReg(),
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);
    }

    private void update() {
        CentroCosto centroCosto = createCentroCosto();
        centroCostoDAO.updateCentroCosto(centroCosto);
        limpiarCampos();
        cargarLista();
    }

    private void cargarLista() {
        List<CentroCosto> lista = centroCostoDAO.readCentrosCosto();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for (CentroCosto centroCosto : lista) {
            Object[] data = {
                    centroCosto.getCodCenCos(),
                    centroCosto.getCenCosNombre(),
                    centroCosto.getCenCostEstReg(),
            };
            modelo.addRow(data);
        }
    }

    private CentroCosto createCentroCosto() {
        CentroCosto centroCosto = new CentroCosto();
        centroCosto.setCodCenCos(ID.getText().charAt(0));
        centroCosto.setCenCosNombre(Descripcion.getText());
        centroCosto.setCenCostEstReg(EstadoRegistro.getText().charAt(0));
        return centroCosto;
    }

    private void limpiarCampos() {
        ID.setText("");
        Descripcion.setText("");
        EstadoRegistro.setText("");
    }

    public static void main(String[] args) {
        CentroCostoForm centroCostoForm = new CentroCostoForm(null);
    }
}
