import org.app.Trabajador;
import org.app.TrabajadorDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.Date;
import java.util.List;

public class TrabajadorTableModel extends AbstractTableModel implements TableModel {
    private List<Trabajador> trabajadores;

    private final String[] columnNames = {
            "ID", "Nombre", "Tipo de Trabajo", "Fecha de Ingreso", "Fecha de Cese", "Última Salida de Vacaciones", "Estado de Trabajo", "Estado de Registro", "Cuenta Corriente Número", "Código Centro Costo"
    };

    public TrabajadorTableModel(List<Trabajador> trabajadores) {
        System.out.println("Se agrego trabajadores ");
        this.trabajadores = trabajadores;
    }

    @Override
    public int getRowCount() {
        return trabajadores.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }


    @Override
    public String getColumnName(int columnIndex) {
        System.out.println("column" + columnNames[1]);
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Character.class;
            case 3:
            case 4:
            case 5:
                return Date.class;
            case 6:
            case 7:
                return Character.class;
            case 8:
                return String.class;
            case 9:
                return Character.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Trabajador trabajador = trabajadores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return trabajador.getTrabajadorId();
            case 1:
                return trabajador.getTrabajadorNombre();
            case 2:
                return trabajador.getTrabajaTipoTraId();
            case 3:
                return trabajador.getCodCenCos();
            case 4:
                return trabajador.getTraEstTra();
            case 5:
                return trabajador.getCueCorNum();
            case 6:
                return trabajador.getTraEstRegTra();
            case 7:
                return trabajador.getTraFecIng();
            case 8:
                return trabajador.getTraFecCes();
            case 9:
                return trabajador.getTraFecUltSalVac();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        for (int i = 0; i < getRowCount(); i++) {
            Trabajador trabajador = trabajadores.get(i);
            switch (columnIndex) {
                case 0:
                    trabajador.setTrabajadorId((Integer) aValue);
                    break;
                case 1:
                    trabajador.setTrabajadorNombre((String) aValue);
                    break;
                case 2:
                    trabajador.setTrabajaTipoTraId(Character.highSurrogate((Integer) aValue));
                    break;
                case 3:
                    trabajador.setTraFecIng((Date) aValue);
                    break;
                case 4:
                    trabajador.setTraFecCes((Date) aValue);
                    break;
                case 5:
                    trabajador.setTraFecUltSalVac((Date) aValue);
                    break;
                case 6:
                    trabajador.setTraEstTra((Character) aValue);
                    break;
                case 7:
                    trabajador.setTraEstRegTra((Character) aValue);
                    break;
                case 8:
                    trabajador.setCueCorNum((String) aValue);
                    break;
                case 9:
                    trabajador.setCodCenCos((Character) aValue);
                    break;
            }
        }

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
