package org.app.forms;

import org.app.controllers.Trabajador;
import org.app.dao.TrabajadorDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;


public class TrabajadorForm extends JDialog{

    private TrabajadorDAO trabajadordao;

    private JTextField trabajadorId;
    private JTextField trabajadorNombre;
    private JTextField trabajaTipoTraId;
    private JTextField codCenCos;
    private JTextField traEstTra;
    private JTextField cueCorNum;
    private JTextField traEstRegTra;

    private JPanel trabajadorPanel;
    private JTable results;
    private JButton adicionarButton;
    private JButton salirButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton cancelarButton;
    private JButton inactivarButton;
    private JButton reactivarButton;
    private JButton actualizarButton;
    private JSpinner spinnerIng;
    private JSpinner spinnerCes;
    private JSpinner spinnerVacas;
    private JScrollPane scrollResults;


    public TrabajadorForm(JFrame parent){
        super(parent);
        setTitle("org.app.controllers.Trabajador");
        setContentPane(trabajadorPanel);
        setMinimumSize(new Dimension(1400, 474));

        trabajadordao = new TrabajadorDAO();
        adicionarButton.addActionListener(e -> {

            Trabajador trabajador = createTrabajador();
            trabajadordao.createTrabajador(trabajador);
            limpiarCampos();
            cargarLista();
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

                trabajadordao.deleteTrabajador(Integer.parseInt(trabajadorId.getText()));
                limpiarCampos();
                cargarLista();


            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarLista();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Entrando a cancelar");
                results.clearSelection();
                limpiarCampos();
                //cargarLista();
                trabajadorId.setEnabled(true);
            }
        });
        // ListSelectionListener para la tabla
        results.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && results.getSelectedRow() != -1) {
                    int selectedRow = results.getSelectedRow();
                    trabajadorId.setText(results.getValueAt(selectedRow, 0).toString());
                    trabajadorNombre.setText(results.getValueAt(selectedRow, 1).toString());
                    trabajaTipoTraId.setText(results.getValueAt(selectedRow, 2).toString());
                    spinnerIng.setValue(results.getValueAt(selectedRow, 3));
                    spinnerCes.setValue(results.getValueAt(selectedRow, 4));
                    spinnerVacas.setValue(results.getValueAt(selectedRow, 5));
                    traEstTra.setText(results.getValueAt(selectedRow, 6).toString());
                    traEstRegTra.setText(results.getValueAt(selectedRow, 7).toString());
                    cueCorNum.setText(results.getValueAt(selectedRow, 8).toString());
                    codCenCos.setText(results.getValueAt(selectedRow, 9).toString());
                    trabajadorId.setEnabled(false);
                }

            }
        });
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inactivar();
                cargarLista();
            }
        });
        reactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activar();
                cargarLista();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cerrar la ventana
                dispose();
            }
        });

        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);


    }
    private void activar (){
        //activar el estado de registro de trabajador
        trabajadordao.activarTrabajador(Integer.parseInt(trabajadorId.getText()));
        traEstRegTra.setText("A");
    }
    private void inactivar(){
        //inactiar el estado de registro de trabajador
        trabajadordao.inactivarTrabajador(Integer.parseInt(trabajadorId.getText()));
        traEstRegTra.setText("I");

    }
    private void update(){
        Trabajador trabajador = createTrabajador();
        trabajadordao.updateTrabajador(trabajador);
        limpiarCampos();
        cargarLista();
    }

    private void createUIComponents() {

        // TODO: place custom component creation code here
        SpinnerDateModel modelIng = new SpinnerDateModel();
        SpinnerDateModel modelCes = new SpinnerDateModel();
        SpinnerDateModel modelVacas = new SpinnerDateModel();
        spinnerIng = new JSpinner(modelIng);
        spinnerCes = new JSpinner(modelCes);
        spinnerVacas = new JSpinner(modelVacas);

        // Configurar formato de fecha para los spinners
        JSpinner.DateEditor editorIng = new JSpinner.DateEditor(spinnerIng, "dd/MM/yyyy");
        JSpinner.DateEditor editorCes = new JSpinner.DateEditor(spinnerCes, "dd/MM/yyyy");
        JSpinner.DateEditor editorVacas = new JSpinner.DateEditor(spinnerVacas, "dd/MM/yyyy");
        spinnerIng.setEditor(editorIng);
        spinnerCes.setEditor(editorCes);
        spinnerVacas.setEditor(editorVacas);

        //tableeeeeeeeeee
        DefaultTableModel modelDefault = new DefaultTableModel();

        modelDefault.setColumnIdentifiers(new String[] {
                "ID", "Nombre", "Tipo de Trabajo", "Fecha de Ingreso", "Fecha de Cese", "Última Salida de Vacaciones",
                "Estado de Trabajo", "Estado de Registro", "Cuenta Corriente Número", "Código Centro Costo"
        });
        // Cargar datos desde el DAO
        trabajadordao = new TrabajadorDAO();
        System.out.println(trabajadordao);
        List<Trabajador> trabajadores = trabajadordao.readTrabajadores();
        System.out.println(trabajadores);
        for (Trabajador trabajador : trabajadores) {
            Object[] rowData = {
                    trabajador.getTrabajadorId(),
                    trabajador.getTrabajadorNombre(),
                    trabajador.getTrabajaTipoTraId(),
                    trabajador.getTraFecIng(),
                    trabajador.getTraFecCes(),
                    trabajador.getTraFecUltSalVac(),
                    trabajador.getTraEstTra(),
                    trabajador.getTraEstRegTra(),
                    trabajador.getCueCorNum(),
                    trabajador.getCodCenCos()
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);


    }

    private void cargarLista() {
        List<Trabajador> lista = trabajadordao.readTrabajadores();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for(Trabajador obj : lista) {
            Object[] data = {obj.getTrabajadorId(),
                             obj.getTrabajadorNombre(),
                             obj.getTrabajaTipoTraId(),
                             obj.getTraFecIng(),
                             obj.getTraFecCes(),
                             obj.getTraFecUltSalVac(),
                             obj.getTraEstTra(),
                             obj.getTraEstRegTra(),
                             obj.getCueCorNum(),
                             obj.getCodCenCos()};
            modelo.addRow(data);
        }
    }

    private Trabajador createTrabajador() {
        Trabajador trabajador = new Trabajador();
        trabajador.setTrabajadorId(Integer.parseInt(trabajadorId.getText()));
        trabajador.setTrabajadorNombre(trabajadorNombre.getText());
        trabajador.setTrabajaTipoTraId(trabajaTipoTraId.getText().charAt(0));
        trabajador.setCodCenCos(codCenCos.getText().charAt(0));
        trabajador.setTraEstTra(traEstRegTra.getText().charAt(0));
        trabajador.setCueCorNum(cueCorNum.getText());
        trabajador.setTraEstRegTra(traEstTra.getText().charAt(0));

        //Fechas
        trabajador.setTraFecIng((Date) spinnerIng.getValue());
        trabajador.setTraFecCes((Date) spinnerCes.getValue());
        trabajador.setTraFecUltSalVac((Date) spinnerVacas.getValue());
        //private JSpinner spinnerCes;
        //private JSpinner spinnerVacas;

        return trabajador;
    }


    private void limpiarCampos() {
        trabajadorId.setText("");
        trabajadorNombre.setText("");
        trabajaTipoTraId.setText("");
        codCenCos.setText("");
        traEstTra.setText("");
        cueCorNum.setText("");
        traEstRegTra.setText("");

        // Puedes agregar más limpieza para otros componentes como JSpinner si es necesario
    }


    public static void main(String[] args) {

        TrabajadorForm trabajadorForm = new TrabajadorForm(null);

    }



}

