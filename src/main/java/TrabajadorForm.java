import org.app.Trabajador;
import org.app.TrabajadorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TrabajadorForm extends JDialog{

    private TrabajadorDAO trabajadordao = new TrabajadorDAO();

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


    public TrabajadorForm(JFrame parent){
        super(parent);
        setTitle("Trabajador");
        setContentPane(trabajadorPanel);
        setMinimumSize(new Dimension(816, 474));

        trabajadordao = new TrabajadorDAO();
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Trabajador trabajador = createTrabajador();
                trabajadordao.createTrabajador(trabajador);
                limpiarCampos();

            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trabajadordao.deleteTrabajador(6);
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                List<Trabajador> trabajadores = trabajadordao.readTrabajadores();
//                TrabajadorTableModel model = new TrabajadorTableModel(trabajadores);
//                results.setModel((TableModel) model); // Establecer el modelo en la tabla
            }
        });

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
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

        //table
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

    private void refreshTable() {
        List<Trabajador> trabajadores = trabajadordao.readTrabajadores();
        //modelDefault.setRowCount(0); // Limpiar filas existentes
        for (Trabajador trabajador : trabajadores) {
            Object[] rowData = {
                    trabajador.getTrabajadorId(), trabajador.getTrabajadorNombre(), trabajador.getTrabajaTipoTraId(),
                    trabajador.getTraFecIng(), trabajador.getTraFecCes(), trabajador.getTraFecUltSalVac(),
                    trabajador.getTraEstTra(), trabajador.getTraEstRegTra(), trabajador.getCueCorNum(),
                    trabajador.getCodCenCos()
            };
            //modelDefault.addRow(rowData);
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

