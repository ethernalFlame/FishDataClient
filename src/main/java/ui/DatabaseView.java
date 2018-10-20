package ui;

import client.FishBean;
import controller.DeleteController;
import controller.EditController;
import data.FishDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DatabaseView extends JFrame {

    FileTableModel fileTableModel;
    ArrayList<FishBean> fishes;
    JTable jTable;
    JButton deleteButton, createNew, editButton;
    JPanel buttonPanel;
    private FishDao fishDao;

    public DatabaseView(List<FishBean> fishes, FishDao fishDao) {
        this.fishDao = fishDao;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1980, 1020);

//        fishes.add(new FishBean("рыба простая", "семейство хорошее", "лужа", "Полиэтилен", "1000", "1кг"));
//        fishes.add(new FishBean("рыба сложная", "семейство прекрасное", "большая лужа", "золото", "9999", "19кг"));

        fileTableModel = new FileTableModel(fishes);
        jTable = new JTable(fileTableModel);
        jTable.setEditingRow(0);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = jTable.rowAtPoint(e.getPoint());
                if (r >= 0 && r < jTable.getRowCount()) {
                    jTable.setRowSelectionInterval(r, r);
                } else {
                    jTable.clearSelection();
                }

                int rowindex = jTable.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    JPopupMenu popup = new JPopupMenu("lol");
                    popup.add(editButton);
                    popup.add(deleteButton);
                    System.out.println("kek");
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new DeleteController(jTable, fishDao));
        editButton = new JButton("Редактировать");
        editButton.addActionListener(new  EditController(fishDao, this, jTable));
        createNew = new JButton("Создать новую запись");

        buttonPanel = new JPanel();
        buttonPanel.add(createNew);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        JScrollPane jScrollPane = new JScrollPane(jTable);
        JPanel dataPanel = new JPanel();
        dataPanel.add(jScrollPane, BorderLayout.CENTER);
        dataPanel.add(jTable.getTableHeader(), BorderLayout.NORTH);
        add(jScrollPane);
        add(buttonPanel, BorderLayout.SOUTH);
        add(dataPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    public void updateFishes() {
        ArrayList<FishBean> all = fishDao.getAll();
        ((FileTableModel) jTable.getModel()).setFishes(all);
        fileTableModel.setFishes(all);
        this.fishes = all;
        ((FileTableModel) jTable.getModel()).fireTableDataChanged();
        jTable.repaint();
    }

    public FileTableModel getFileTableModel() {
        return fileTableModel;
    }

    public void setFileTableModel(FileTableModel fileTableModel) {
        this.fileTableModel = fileTableModel;
    }

    public ArrayList<FishBean> getFishes() {
        return fishes;
    }

    public void setFishes(ArrayList<FishBean> fishes) {
        this.fishes = fishes;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getCreateNew() {
        return createNew;
    }

    public void setCreateNew(JButton createNew) {
        this.createNew = createNew;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public FishDao getFishDao() {
        return fishDao;
    }

    public void setFishDao(FishDao fishDao) {
        this.fishDao = fishDao;
    }
}
