package org.app.forms.TablasReferencialCompania;

import org.app.dao.TipoTrabajadorDAO;

import javax.swing.*;
import java.awt.*;

public abstract class TablasReferencialesEmpresa extends JDialog {
    protected TipoTrabajadorDAO tipoTrabajadorDAO;
    protected JPanel mainPanel;  // Cambié el nombre para ser más general
    protected JTextField ID;
    protected JTextField Descripcion;
    protected JTextField Direccion;
    protected JTextField EstadoRegistro;
    protected JTable results;
    protected JButton agregarButton;
    protected JButton eliminarButton;
    protected JButton modificarButton;
    protected JButton inactivarButton;
    protected JButton activarButton;
    protected JButton salirButton;
    protected JButton cancelarButton;
    protected JLabel NombreTabla;


    public TablasReferencialesEmpresa(JFrame frame) {
        super(frame);
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(800, 470));
        setModal(true);
        setLocationRelativeTo(frame);
    }
    protected abstract void createUIComponents();
}
