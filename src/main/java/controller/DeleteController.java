package controller;

import data.FishDao;
import ui.FileTableModel;

import javax.swing.*;;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteController implements ActionListener {

    private JTable jTable;
    private FishDao fishDao;

    public DeleteController(JTable jTable, FishDao fishDao) {
        this.jTable = jTable;
        this.fishDao = fishDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        fishDao.delete((long)jTable.getValueAt(selectedRow, 0));

        ((FileTableModel) jTable.getModel()).getFishes().remove(selectedRow);
        ((FileTableModel) jTable.getModel()).fireTableDataChanged();

        jTable.repaint();
    }
}
