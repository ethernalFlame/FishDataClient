package ui;

import client.ColAndRow;
import client.FishBean;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileTableModel extends AbstractTableModel {

    private Set<TableModelListener> listeners = new HashSet<>();

    public Set<TableModelListener> getListeners() {
        return listeners;
    }

    public void setListeners(Set<TableModelListener> listeners) {
        this.listeners = listeners;
    }

    public List<FishBean> getFishes() {
        return fishes;
    }

    public void setFishes(List<FishBean> fishes) {
        this.fishes = fishes;
    }

    private List<FishBean> fishes;

    public FileTableModel(List<FishBean> fishes){
        this.fishes = fishes;
    }

    @Override
    public int getRowCount() {
        return fishes.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "ID";
            case 1:
                return "Имя";
            case 2:
                return "Тип";
            case 3:
                return "Семейство";
            case 4:
                return "Среда обитания";
            case 5:
                return "Упаковка";
            case 6:
                return "Способ обработки";
            case 7:
                return "Вес";
            case 8:
                return "Товарная ценность";
        }
        return "pudge";
    }

    public Set<ColAndRow> getEditableCells() {
        return editableCells;
    }

    public void setEditableCells(Set<ColAndRow> editableCells) {
        this.editableCells = editableCells;
    }

    private Set<ColAndRow> editableCells = new HashSet<>();

    public void setRowEditable(int rowIndex) {
        for (int i = 0; i < getColumnCount(); i++) {
            editableCells.add(new ColAndRow(rowIndex, i));
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editableCells.contains(new ColAndRow(rowIndex, columnIndex));
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FishBean fishBean = fishes.get(rowIndex);
        switch (columnIndex){
            case 0:
                return fishBean.getId();
            case 1:
                return fishBean.getName();
            case 2:
                return fishBean.getType();
            case 3:
                return fishBean.getFamily().getName();
            case 4:
                return fishBean.getFamily().getAreol();
            case 5:
                return fishBean.getPack();
            case 6:
                return fishBean.getProcessing();
            case 7:
                return fishBean.getWeigh();
            case 8:
                return fishBean.getValue();
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
