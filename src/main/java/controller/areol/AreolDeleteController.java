package controller.areol;

import data.AreolDao;
import ui.areol.AreolTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreolDeleteController implements ActionListener {
    private JTable jTable;
    private AreolDao areolDao;

    public AreolDeleteController(JTable jTable, AreolDao areolDao) {
        this.jTable = jTable;
        this.areolDao = areolDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        areolDao.delete((long) jTable.getValueAt(selectedRow, 0));

        ((AreolTableModel) jTable.getModel()).getAreolDtos().remove(selectedRow);
        ((AreolTableModel) jTable.getModel()).fireTableDataChanged();

        jTable.repaint();
    }
}
