package ui.fish_package;

import client.PackageDto;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PackageTableModel extends AbstractTableModel {

    private Set<TableModelListener> listeners = new HashSet<>();

    public Set<TableModelListener> getListeners() {
        return listeners;
    }

    public void setListeners(Set<TableModelListener> listeners) {
        this.listeners = listeners;
    }

    public List<PackageDto> getPackageDtos() {
        return packageDtos;
    }

    public void setPackageDtos(List<PackageDto> packageDtos) {
        this.packageDtos = packageDtos;
    }

    private List<PackageDto> packageDtos;

    public PackageTableModel(List<PackageDto> packageDtos) {
        this.packageDtos = packageDtos;
    }

    @Override
    public int getRowCount() {
        return packageDtos.size();
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
        PackageDto packageDto = packageDtos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return packageDto.getId();
            case 1:
                return packageDto.getName();
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
