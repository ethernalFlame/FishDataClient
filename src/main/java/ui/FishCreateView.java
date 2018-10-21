package ui;

import client.FamilyBean;
import client.FishBean;
import data.FishDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FishCreateView extends JFrame {
    private final JButton jButton;
    private final JButton jButton1;
    private JLabel jLabel;
    private JTextField jTextField;
    private JLabel jLabel1;
    private JTextField jTextField1;
    private JLabel jLabel2;
    private JTextField jTextField2;
    private JLabel jLabel3;
    private JTextField jTextField3;
    private JLabel jLabel4;
    private JTextField jTextField4;
    private JLabel jLabel5;
    private JTextField jTextField5;
    private JLabel jLabel6;
    private JTextField jTextField6;
    private JLabel jLabel7;
    private JTextField jTextField7;
    private FishDao fishDao;
    private DatabaseView databaseView;

    public FishCreateView(FishDao fishDao, DatabaseView databaseView) {
        this.fishDao = fishDao;
        this.databaseView = databaseView;

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(0, 2));
        setVisible(true);
        initLabels();
        jButton = new JButton("Очистить");
        jButton.addActionListener(e -> clear());
        add(jButton);
        jButton1 = new JButton("Подтвердить");
        jButton1.addActionListener(e -> {
            try {
                this.fishDao.save(mapToFishBean());
                databaseView.updateFishes();
           //     ((FileTableModel)databaseView.getjTable().getModel()).setRowCount(0);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        add(jButton1);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                databaseView.updateFishes();
                ((FileTableModel) databaseView.getjTable().getModel()).fireTableDataChanged();
            }
        });
    }

    private FishBean mapToFishBean() {
        return new FishBean(0L, jTextField1.getText(), jTextField.getText(), new FamilyBean(jTextField2.getText(), jTextField3.getText()),
                jTextField4.getText(), jTextField5.getText(), Double.parseDouble(jTextField6.getText()), Double.parseDouble(jTextField7.getText()));
    }

    public void initLabels() {
        jLabel = new JLabel("Название");
        jTextField = new JTextField();
        jLabel1 = new JLabel("Тип");
        jTextField1 = new JTextField();
        jLabel2 = new JLabel("Семейство");
        jTextField2 = new JTextField();
        jLabel3 = new JLabel("Среда обитания");
        jTextField3 = new JTextField();
        jLabel4 = new JLabel("Упаковка");
        jTextField4 = new JTextField();
        jLabel5 = new JLabel("Способ обработки");
        jTextField5 = new JTextField();
        jLabel6 = new JLabel("Вес");
        jTextField6 = new JTextField();
        jLabel7 = new JLabel("Товарная ценность");
        jTextField7 = new JTextField();
        add(jLabel);
        add(jTextField);
        add(jLabel1);
        add(jTextField1);
        add(jLabel2);
        add(jTextField2);
        add(jLabel3);
        add(jTextField3);
        add(jLabel4);
        add(jTextField4);
        add(jLabel5);
        add(jTextField5);
        add(jLabel6);
        add(jTextField6);
        add(jLabel7);
        add(jTextField7);
    }

    private void clear() {
        jTextField.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
    }

}
