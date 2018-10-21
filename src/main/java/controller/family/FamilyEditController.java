package controller.family;

import client.FamilyDto;
import data.FamilyDao;
import ui.family.FamilyEditView;
import ui.family.FamilyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FamilyEditController implements ActionListener {
    private FamilyDao familyDao;
    private FamilyView familyView;
    private JTable jTable;

    public FamilyEditController(FamilyDao familyDao, FamilyView familyView, JTable jTable) {
        this.familyDao = familyDao;
        this.familyView = familyView;
        this.jTable = jTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        FamilyDto familyDto = mapToFamilyDto(selectedRow);
        new FamilyEditView(familyDto, familyDao, familyView);
    }

    private FamilyDto mapToFamilyDto(int selectedRow) {
        return new FamilyDto((long) jTable.getValueAt(selectedRow, 0), (String) jTable.getValueAt(selectedRow, 1),
                (long) jTable.getValueAt(selectedRow, 2), (String) jTable.getValueAt(selectedRow, 3));
    }
}
