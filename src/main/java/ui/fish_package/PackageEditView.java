package ui.fish_package;

import client.PackageDto;
import data.PackageDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PackageEditView extends JFrame {
    private final JButton jButton;
    private final JButton jButton1;
    private PackageDto packageDto;
    private JLabel jLabel;
    private JTextField jTextField;
    private PackageDao packageDao;
    private PackageView packageView;

    public PackageEditView(PackageDto packageDto, PackageDao packageDao, PackageView packageView) {
        this.packageDao = packageDao;
        this.packageView= packageView;
        this.packageDto= packageDto;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(0, 2));
        initLabels(packageDto);
        setVisible(true);
        jButton = new JButton("Очистить");
        jButton.addActionListener(e -> clear(packageDto));
        add(jButton);
        jButton1 = new JButton("Подтвердить");
        jButton1.addActionListener(e -> {
            this.packageDao.update(mapToPackage());
            packageView.updatePackage();
        });
        add(jButton1);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                packageView.updatePackage();
                ((PackageTableModel) packageView.getjTable().getModel()).fireTableDataChanged();
            }
        });
    }

    private PackageDto mapToPackage() {
        return new PackageDto(this.packageDto.getId(), jTextField.getText());
    }

    public void initLabels(PackageDto packageDto) {
        jLabel = new JLabel("Название");
        jTextField = new JTextField(packageDto.getName());
        add(jLabel);
        add(jTextField);
    }

    private void clear(PackageDto packageDto) {
        jTextField.setText(packageDto.getName());
    }
}
