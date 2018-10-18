package ui;
import client.FishBean;
import controller.DeleteController;
import data.FishDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DatabaseView extends JFrame {

    FileTableModel fileTableModel;
    ArrayList<FishBean> fishes;
    JTable jTable;
    JButton deleteButton, createNew, editButton;
    JPanel buttonPanel;
    private FishDao fishDao;

    public DatabaseView(List<FishBean> fishes, FishDao fishDao){
        this.fishDao = fishDao;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1980,1020);

//        fishes.add(new FishBean("рыба простая", "семейство хорошее", "лужа", "Полиэтилен", "1000", "1кг"));
//        fishes.add(new FishBean("рыба сложная", "семейство прекрасное", "большая лужа", "золото", "9999", "19кг"));

        fileTableModel = new FileTableModel(fishes);
        jTable = new JTable(fileTableModel);
        jTable.setEditingRow(0);

        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new DeleteController(jTable, fishDao));
        editButton = new JButton("Редактировать");
        createNew = new JButton("Создать новую запись");

        buttonPanel = new JPanel();
        buttonPanel.add(createNew);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(jTable, BorderLayout.CENTER);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
