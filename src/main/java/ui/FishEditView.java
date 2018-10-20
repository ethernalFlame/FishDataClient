package ui;

import client.FamilyBean;
import client.FishBean;
import data.FishDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FishEditView extends JFrame {
    private final JButton jButton;
    private final JButton jButton1;
    private FishBean fishBean;
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

    public FishEditView(FishBean fishBean, FishDao fishDao, DatabaseView databaseView) {
        this.fishDao = fishDao;
        this.databaseView = databaseView;
        this.fishBean = fishBean;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(0, 2));
        initLabels(fishBean);
        setVisible(true);
        jButton = new JButton("Очистить");
        jButton.addActionListener(e -> clear(fishBean));
        add(jButton);
        jButton1 = new JButton("Подтвердить");
        jButton1.addActionListener(e ->this. fishDao.update(mapToFishBean()));
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
        return new FishBean(this.fishBean.getId(), jTextField.getText(), jTextField1.getText(), new FamilyBean(jTextField2.getText(), jTextField3.getText()),
                jTextField4.getText(), jTextField5.getText(), Double.parseDouble(jTextField6.getText()), Double.parseDouble(jTextField7.getText()));
    }

    public void initLabels(FishBean fishBean) {
        jLabel = new JLabel("Название");
        jTextField = new JTextField(fishBean.getName());
        jLabel1 = new JLabel("Тип");
        jTextField1 = new JTextField(fishBean.getType());
        jLabel2 = new JLabel("Семейство");
        jTextField2 = new JTextField(fishBean.getFamily().getName());
        jLabel3 = new JLabel("Среда обитания");
        jTextField3 = new JTextField(fishBean.getFamily().getAreol());
        jLabel4 = new JLabel("Упаковка");
        jTextField4 = new JTextField(fishBean.getPack());
        jLabel5 = new JLabel("Способ обработки");
        jTextField5 = new JTextField(fishBean.getProcessing());
        jLabel6 = new JLabel("Вес");
        jTextField6 = new JTextField(String.valueOf(fishBean.getWeigh()));
        jLabel7 = new JLabel("Товарная ценность");
        jTextField7 = new JTextField(String.valueOf(fishBean.getValue()));
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

    private void clear(FishBean fishBean) {
        jTextField.setText(fishBean.getName());
        jTextField1.setText(fishBean.getType());
        jTextField2.setText(fishBean.getFamily().getName());
        jTextField3.setText(fishBean.getFamily().getAreol());
        jTextField4.setText(fishBean.getPack());
        jTextField5.setText(fishBean.getProcessing());
        jTextField6.setText(String.valueOf(fishBean.getWeigh()));
        jTextField7.setText(String.valueOf(fishBean.getValue()));
    }

    public FishBean getFishBean() {
        return fishBean;
    }

    public void setFishBean(FishBean fishBean) {
        this.fishBean = fishBean;
    }
}
