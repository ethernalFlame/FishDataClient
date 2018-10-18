package ui;

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
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Имя";
            case 1:
                return "Тип";
            case 2:
                return "Семейство";
            case 3:
                return "Среда обитания";
            case 4:
                return "Упаковка";
            case 5:
                return "Способ обработки";
            case 6:
                return "Вес";
            case 7:
                return "Товарная ценность";
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
