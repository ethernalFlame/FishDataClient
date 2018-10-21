package controller.areol;

import client.AreolDto;
import data.AreolDao;
import ui.areol.AreolEditView;
import ui.areol.AreolView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreolEditController implements ActionListener {
    private AreolDao areolDao;
    private AreolView areolView;
    private JTable jTable;

    public AreolEditController(AreolDao areolDao, AreolView areolView, JTable jTable) {
        this.areolDao = areolDao;
        this.areolView = areolView;
        this.jTable = jTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        AreolDto areolDto = mapToAreolDto(selectedRow);
        new AreolEditView(areolDto, areolDao, areolView);
    }

    private AreolDto mapToAreolDto(int selectedRow) {
        return new AreolDto((long) jTable.getValueAt(selectedRow, 0), (String) jTable.getValueAt(selectedRow, 1));
    }
}
