package controller.fish_package;

import data.PackageDao;
import ui.fish_package.PackageCreateView;
import ui.fish_package.PackageView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PackageCreateController implements ActionListener {

    private PackageDao packageDao;
    private PackageView packageView;

    public PackageCreateController(PackageDao packageDao, PackageView packageView) {
        this.packageDao = packageDao;
        this.packageView = packageView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new PackageCreateView(packageDao, packageView);
    }
}
