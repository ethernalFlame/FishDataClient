package ui;

import client.ProcessingDto;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProcessingTableModel extends AbstractTableModel {
    private Set<TableModelListener> listeners = new HashSet<>();

    public Set<TableModelListener> getListeners() {
        return listeners;
    }

    public void setListeners(Set<TableModelListener> listeners) {
        this.listeners = listeners;
    }

    public List<ProcessingDto> getProcessingDtos() {
        return processingDtos;
    }

    public void setProcessingDtos(List<ProcessingDto> processingDtos) {
        this.processingDtos = processingDtos;
    }

    private List<ProcessingDto> processingDtos;

    public ProcessingTableModel(List<ProcessingDto> processingDtos){
        this.processingDtos = processingDtos;
    }

    @Override
    public int getRowCount() {
        return processingDtos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "ID";
            case 1:
                return "Имя";
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
        ProcessingDto processingDto = processingDtos.get(rowIndex);
        switch (columnIndex){
            case 0:
                return processingDto.getId();
            case 1:
                return processingDto.getName();
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
