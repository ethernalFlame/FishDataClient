package ui;

import client.FishBean;
import controller.CreateController;
import controller.DeleteController;
import controller.EditController;
import data.FishDao;
import ui.areol.AreolView;
import ui.fish_package.PackageView;
import ui.processing.ProcessingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DatabaseView extends JFrame {

    ArrayList<FishBean> fishes;
    JTable jTable;
    JButton deleteButton, createNew, editButton, processingTable, packageTable, areolTable;
    JPanel buttonPanel;
    private FishDao fishDao;

    public DatabaseView(List<FishBean> fishes, FishDao fishDao) {
        this.fishDao = fishDao;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);

        FileTableModel fileTableModel = new FileTableModel(fishes);
        jTable = new JTable(fileTableModel);
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
        createNew.addActionListener(new CreateController(fishDao, this));
        processingTable = new JButton("Таблица \"способ обработки\"");
        processingTable.addActionListener(e -> new ProcessingView(fishDao.getProcessingDao().getAll(), fishDao.getProcessingDao(), DatabaseView.this));
        packageTable = new JButton("Таблица \"упаковка\"");
        packageTable.addActionListener(e -> new PackageView(fishDao.getPackageDao().getAll(), fishDao.getPackageDao(), DatabaseView.this));
        areolTable = new JButton("Таблица \"Среда обитания\"");
        areolTable.addActionListener(e -> new AreolView(fishDao.getAreolDao().getAll(), fishDao.getAreolDao(), DatabaseView.this));

        buttonPanel = new JPanel();
        buttonPanel.add(createNew);
        buttonPanel.add(processingTable);
        buttonPanel.add(packageTable);
        buttonPanel.add(areolTable);
        buttonPanel.setVisible(true);

        JScrollPane jScrollPane = new JScrollPane(jTable,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel dataPanel = new JPanel();
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        dataPanel.add(jScrollPane, BorderLayout.CENTER);
        dataPanel.add(jTable.getTableHeader(), BorderLayout.NORTH);
        add(jScrollPane);
        add(buttonPanel, BorderLayout.SOUTH);
        add(dataPanel, BorderLayout.NORTH);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void updateFishes() {
        ArrayList<FishBean> all = fishDao.getAll();
        jTable.setModel(new FileTableModel(all));
        this.fishes = all;

  //
        jTable.repaint();
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
