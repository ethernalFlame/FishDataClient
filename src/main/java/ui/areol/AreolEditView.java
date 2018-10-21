package ui.areol;

import client.AreolDto;
import data.AreolDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AreolEditView extends JFrame {
    private final JButton jButton;
    private final JButton jButton1;
    private AreolDto areolDto;
    private JLabel jLabel;
    private JTextField jTextField;
    private AreolDao areolDao;
    private AreolView areolView;

    public AreolEditView(AreolDto areolDto, AreolDao areolDao, AreolView areolView) {
        this.areolDao = areolDao;
        this.areolView = areolView;
        this.areolDto = areolDto;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(0, 2));
        initLabels(areolDto);
        setVisible(true);
        jButton = new JButton("Очистить");
        jButton.addActionListener(e -> clear(areolDto));
        add(jButton);
        jButton1 = new JButton("Подтвердить");
        jButton1.addActionListener(e -> {
            this.areolDao.update(mapToAreolDto());
            areolView.updatePackage();
        });
        add(jButton1);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                areolView.updatePackage();
                ((AreolTableModel) areolView.getjTable().getModel()).fireTableDataChanged();
            }
        });
    }

    private AreolDto mapToAreolDto() {
        return new AreolDto(this.areolDto.getId(), jTextField.getText());
    }

    public void initLabels(AreolDto areolDto) {
        jLabel = new JLabel("Название");
        jTextField = new JTextField(areolDto.getName());
        add(jLabel);
        add(jTextField);
    }

    private void clear(AreolDto areolDto) {
        jTextField.setText(areolDto.getName());
    }
}
