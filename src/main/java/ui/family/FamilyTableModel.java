package ui.family;

import client.FamilyDto;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FamilyTableModel extends AbstractTableModel {

    private Set<TableModelListener> listeners = new HashSet<>();
    private List<FamilyDto> familyDtos;

    public FamilyTableModel(List<FamilyDto> familyDtos) {
        this.familyDtos = familyDtos;
    }

    public Set<TableModelListener> getListeners() {
        return listeners;
    }

    public List<FamilyDto> getFamilyDtos() {
        return familyDtos;
    }

    public void setFamilyDtos(List<FamilyDto> familyDtos) {
        this.familyDtos = familyDtos;
    }

    @Override
    public int getRowCount() {
        return familyDtos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Название";
            case 2:
                return "ID среда обитания";
            case 3:
                return "Среда обитания";
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
        FamilyDto familyDto = familyDtos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return familyDto.getId();
            case 1:
                return familyDto.getName();
            case 2:
                return familyDto.getAreolId();
            case 3:
                return familyDto.getAreolName();
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
