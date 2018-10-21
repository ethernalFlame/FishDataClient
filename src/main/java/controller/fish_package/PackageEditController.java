package controller.fish_package;

import client.PackageDto;
import data.PackageDao;
import ui.fish_package.PackageEditView;
import ui.fish_package.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PackageEditController implements ActionListener {
    private PackageDao packageDao;
    private PackageView packageView;
    private JTable jTable;

    public PackageEditController(PackageDao packageDao, PackageView packageView, JTable jTable) {
        this.packageDao = packageDao;
        this.packageView = packageView;
        this.jTable = jTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        PackageDto packageDto = mapToPackageDto(selectedRow);
        new PackageEditView(packageDto, packageDao, packageView);
    }

    private PackageDto mapToPackageDto(int selectedRow) {
        return new PackageDto((long) jTable.getValueAt(selectedRow, 0), (String) jTable.getValueAt(selectedRow, 1));
    }
}
