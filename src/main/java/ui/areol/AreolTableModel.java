package ui.areol;

import client.AreolDto;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AreolTableModel extends AbstractTableModel {

    private Set<TableModelListener> listeners = new HashSet<>();
    private List<AreolDto> areolDtos;

    public AreolTableModel(List<AreolDto> areolDtos) {
        this.areolDtos = areolDtos;
    }

    public Set<TableModelListener> getListeners() {
        return listeners;
    }

    public List<AreolDto> getAreolDtos() {
        return areolDtos;
    }

    public void setAreolDtos(List<AreolDto> areolDtos) {
        this.areolDtos = areolDtos;
    }

    @Override
    public int getRowCount() {
        return areolDtos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Название";
        }
        return "pudge";
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AreolDto areolDto = areolDtos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return areolDto.getId();
            case 1:
                return areolDto.getName();
        }
        return "kukuha";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}
