package controller;

import client.FamilyBean;
import client.FishBean;
import data.FishDao;
import ui.DatabaseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditController implements ActionListener {

    private FishDao fishDao;
    private DatabaseView databaseView;
    private JTable jTable;

    public EditController(FishDao fishDao, DatabaseView databaseView, JTable jTable) {
        this.fishDao = fishDao;
        this.databaseView = databaseView;
        this.jTable = jTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        FishBean fishBean = mapToFishBean(selectedRow);
        fishDao.update(fishBean, 1L);
    }

    public FishBean mapToFishBean(int selectedRow) {
        return new FishBean((long)jTable.getValueAt(selectedRow, 0), (String)jTable.getValueAt(selectedRow, 2),
                (String) jTable.getValueAt(selectedRow, 1),
                new FamilyBean((String)jTable.getValueAt(selectedRow, 3), (String)jTable.getValueAt(selectedRow, 4)),
                (String)jTable.getValueAt(selectedRow, 5), (String)jTable.getValueAt(selectedRow, 6),
                (double)jTable.getValueAt(selectedRow, 7),  (double)jTable.getValueAt(selectedRow, 8));
    }

    public FishDao getFishDao() {
        return fishDao;
    }

    public void setFishDao(FishDao fishDao) {
        this.fishDao = fishDao;
    }

    public DatabaseView getDatabaseView() {
        return databaseView;
    }

    public void setDatabaseView(DatabaseView databaseView) {
        this.databaseView = databaseView;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }
}
