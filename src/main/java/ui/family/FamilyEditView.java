package ui.family;

import client.FamilyDto;
import data.FamilyDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FamilyEditView extends JFrame {
    private final JButton jButton;
    private final JButton jButton1;
    private FamilyDto familyDto;
    private JLabel jLabel;
    private JTextField jTextField;
    private JLabel jLabel1;
    private JTextField jTextField1;
    private FamilyDao familyDao;
    private FamilyView familyView;

    public FamilyEditView(FamilyDto familyDto, FamilyDao familyDao, FamilyView familyView) {
        this.familyDao = familyDao;
        this.familyView = familyView;
        this.familyDto = familyDto;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(0, 2));
        initLabels(familyDto);
        setVisible(true);
        jButton = new JButton("Очистить");
        jButton.addActionListener(e -> clear(familyDto));
        add(jButton);
        jButton1 = new JButton("Подтвердить");
        jButton1.addActionListener(e -> {
            this.familyDao.update(mapToFamily());
            familyView.updateFamily();
        });
        add(jButton1);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                familyView.updateFamily();
                ((FamilyTableModel) familyView.getjTable().getModel()).fireTableDataChanged();
            }
        });
    }

    private FamilyDto mapToFamily() {
        return new FamilyDto(this.familyDto.getId(), jTextField.getText(), this.familyDto.getAreolId(), jTextField1.getText());
    }

    public void initLabels(FamilyDto familyDto) {
        jLabel = new JLabel("Название");
        jTextField = new JTextField(familyDto.getName());
        jLabel1 = new JLabel("Среда обитания");
        jTextField1 = new JTextField(familyDto.getAreolName());
        add(jLabel);
        add(jTextField);
        add(jLabel1);
        add(jTextField1);
    }

    private void clear(FamilyDto familyDto) {
        jTextField.setText(familyDto.getName());
        jTextField1.setText(familyDto.getAreolName());
    }
}
