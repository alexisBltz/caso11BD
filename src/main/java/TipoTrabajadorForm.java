import org.app.TipoTrabajador;
import org.app.TipoTrabajadorDAO;
import org.app.Trabajador;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class TipoTrabajadorForm extends JDialog{
    private TipoTrabajadorDAO tipoTrabajadorDAO;
    private JTextField tipoTrabajadorID;
    private JTextField tipoTrabajadorDesc;
    private JTextField tipotraEstReg;
    private JPanel tipoTrabajadorPane;
    private JTable results;
    private JButton eliminarButton;
    private JButton modificarButton;
    private JButton salirButton;
    private JButton agregarButton;
    private JButton inactivarButton;
    private JButton activarButton;
    private JButton cancelarButton;


    public TipoTrabajadorForm(JFrame parent) {
        //inicializacion de la ventana
        super(parent);
        setContentPane(tipoTrabajadorPane);
        setMinimumSize(new Dimension(800, 470));
        setModal(true);
        setLocationRelativeTo(parent);
        //Funcion para agregar nuevos tipos de trabajador
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoTrabajador tipoTrabajador = createTipoTrabajador();
                tipoTrabajadorDAO.createtipoTrabajador(tipoTrabajador);
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
        //Funcion para eliminar nuevos tipos de trabajador
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipoTrabajadorDAO.deleteTrabajador(tipoTrabajadorID.getText().charAt(0));
                limpiarCampos();
                cargarLista();
            }
        });

        //Inactivar y activar
        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //inactiar el estado de registro de trabajador
                tipoTrabajadorDAO.inactivarTrabajador(tipoTrabajadorID.getText().charAt(0));
                tipotraEstReg.setText("I");
                cargarLista();
            }
        });
        activarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //activar el estado de registro de trabajador
                tipoTrabajadorDAO.activarTrabajador(tipoTrabajadorID.getText().charAt(0));
                tipotraEstReg.setText("A");
                cargarLista();
            }
        });

        //Borra los campos, para poder agregar a algun trabajador
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        //boton de salida :v
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //funcion que hace la seleccion en la tabla de la interfaz
        results.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && results.getSelectedRow() != -1) {
                    int selectedRow = results.getSelectedRow();
                    tipoTrabajadorID.setText(results.getValueAt(selectedRow, 0).toString());
                    tipoTrabajadorDesc.setText(results.getValueAt(selectedRow, 1).toString());
                    tipotraEstReg.setText(results.getValueAt(selectedRow, 2).toString());
                    tipoTrabajadorID.setEnabled(false);
                }

            }
        });

        //renderizamos
        setVisible(true);
    }


    private void update(){
        TipoTrabajador tipotrabajador = createTipoTrabajador();
        tipoTrabajadorDAO.updateTrabajador(tipotrabajador);
        limpiarCampos();
        cargarLista();
    }

    private void cargarLista() {
        List<TipoTrabajador> lista = tipoTrabajadorDAO.readTipoTrabajadores();

        DefaultTableModel modelo = (DefaultTableModel) results.getModel();
        modelo.getDataVector().clear();

        for(TipoTrabajador obj : lista) {
            Object[] data = {
                    obj.getTrabajadortipoTraId(),
                    obj.getTibtrabDesc(),
                    obj.getTrabEstReg(),
            };
            modelo.addRow(data);
        }
    }

    private TipoTrabajador createTipoTrabajador() {
        TipoTrabajador tipoTrabajador = new TipoTrabajador();
        tipoTrabajador.setTrabajadortipoTraId(tipoTrabajadorID.getText().charAt(0));
        tipoTrabajador.setTibtrabDesc(tipoTrabajadorDesc.getText());
        tipoTrabajador.setTrabEstReg(tipotraEstReg.getText().charAt(0));

        return tipoTrabajador;

    }

    private void limpiarCampos() {
        tipoTrabajadorID.setText("");
        tipoTrabajadorDesc.setText("");
        tipotraEstReg.setText("");
    }

    public static void main(String[] args) {
        TipoTrabajadorForm tipoTrabajador = new TipoTrabajadorForm(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //tableeeeeeeeeee
        tipoTrabajadorDAO = new TipoTrabajadorDAO();
        List<TipoTrabajador> tipoTrabajadores = tipoTrabajadorDAO.readTipoTrabajadores();
        DefaultTableModel modelDefault = new DefaultTableModel();

        modelDefault.setColumnIdentifiers(new String[] {
                "ID", "Descripcion", "Estado de registro"
        });
        for (TipoTrabajador tipoTrabajador : tipoTrabajadores) {
            Object[] rowData = {
                    tipoTrabajador.getTrabajadortipoTraId(),
                    tipoTrabajador.getTibtrabDesc(),
                    tipoTrabajador.getTrabEstReg(),
            };
            modelDefault.addRow(rowData);
        }

        results = new JTable(modelDefault);
    }
}

