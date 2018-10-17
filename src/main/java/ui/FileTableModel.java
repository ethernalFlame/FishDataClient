package ui;

import client.FishBean;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileTableModel implements TableModel {

    private Set<TableModelListener> listeners = new HashSet<>();
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
                return fishBean.getName();
            case 1:
                return fishBean.getType();
            case 2:
                return fishBean.getFamily().getName();
            case 3:
                return fishBean.getFamily().getAreol();
            case 4:
                return fishBean.getPack();
            case 5:
                return fishBean.getProcessing();
            case 6:
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
