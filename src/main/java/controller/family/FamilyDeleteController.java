package controller.family;

import data.FamilyDao;
import ui.family.FamilyTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FamilyDeleteController implements ActionListener {
    private JTable jTable;
    private FamilyDao familyDao;

    public FamilyDeleteController(JTable jTable, FamilyDao familyDao) {
        this.jTable = jTable;
        this.familyDao = familyDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        familyDao.delete((long) jTable.getValueAt(selectedRow, 0), (long) jTable.getValueAt(selectedRow, 2));

        ((FamilyTableModel) jTable.getModel()).getFamilyDtos().remove(selectedRow);
        ((FamilyTableModel) jTable.getModel()).fireTableDataChanged();

        jTable.repaint();
    }
}
