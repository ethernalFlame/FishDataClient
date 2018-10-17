package ui;
import client.FishBean;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DatabaseView extends JFrame {

    FileTableModel fileTableModel;
    ArrayList<FishBean> fishes;
    JTable jTable;
    JButton deleteButton, createNew, editButton;
    JPanel buttonPanel;

    public DatabaseView(){
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1980,1020);

        fishes = new ArrayList<>();
//        fishes.add(new FishBean("рыба простая", "семейство хорошее", "лужа", "Полиэтилен", "1000", "1кг"));
//        fishes.add(new FishBean("рыба сложная", "семейство прекрасное", "большая лужа", "золото", "9999", "19кг"));

        fileTableModel = new FileTableModel(fishes);
        jTable = new JTable(fileTableModel);

        deleteButton = new JButton("Удалить");
        editButton = new JButton("Редактировать");
        createNew = new JButton("Создать новую запись");

        buttonPanel = new JPanel();
        buttonPanel.add(createNew);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(jTable, BorderLayout.CENTER);
        setVisible(true);
    }
}
