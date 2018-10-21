package ui.family;

import data.FamilyDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FamilyCreateView extends JFrame {
    private final JButton jButton;
    private final JButton jButton1;

    private JLabel jLabel, jLabel1;
    private JTextField jTextField, jTextField1;
    private FamilyDao familyDao;
    private FamilyView familyView;

    public FamilyCreateView(FamilyDao familyDao, FamilyView familyView) {
        this.familyDao = familyDao;
        this.familyView = familyView;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(0, 2));
        setVisible(true);
        jButton = new JButton("Очистить");
        jButton.addActionListener(e -> clear());
        initLabels();
        add(jButton);
        jButton1 = new JButton("Подтвердить");
        jButton1.addActionListener(e -> {
            try {
                this.familyDao.save(this.jTextField.getText(), this.jTextField1.getText());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
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

    public void initLabels() {
        jLabel = new JLabel("Название");
        jTextField = new JTextField("");
        jLabel1 = new JLabel("Среда обитания");
        jTextField1 = new JTextField("");
        add(jLabel);
        add(jTextField);
        add(jLabel1);
        add(jTextField1);
    }

    private void clear() {
        jTextField.setText("");
        jTextField1.setText("");
    }
}
