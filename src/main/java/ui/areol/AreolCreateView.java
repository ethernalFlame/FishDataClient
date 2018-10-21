package ui.areol;

import data.AreolDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AreolCreateView extends JFrame {
    private final JButton jButton;
    private final JButton jButton1;

    private JLabel jLabel;
    private JTextField jTextField;
    private AreolDao areolDao;
    private AreolView areolView;

    public AreolCreateView(AreolDao areolDao, AreolView areolView) {
        this.areolDao = areolDao;
        this.areolView = areolView;
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
                this.areolDao.save(this.jTextField.getText());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
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

    public void initLabels() {
        jLabel = new JLabel("Название");
        jTextField = new JTextField("");
        add(jLabel);
        add(jTextField);
    }

    private void clear() {
        jTextField.setText("");
    }
}
