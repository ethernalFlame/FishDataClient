package controller.fish_package;

import data.PackageDao;
import ui.fish_package.PackageTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PackageDeleteController implements ActionListener {
    private JTable jTable;
    private PackageDao packageDao;

    public PackageDeleteController(JTable jTable, PackageDao packageDao) {
        this.jTable = jTable;
        this.packageDao = packageDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        packageDao.delete((long) jTable.getValueAt(selectedRow, 0));

        ((PackageTableModel) jTable.getModel()).getPackageDtos().remove(selectedRow);
        ((PackageTableModel) jTable.getModel()).fireTableDataChanged();

        jTable.repaint();
    }
}
