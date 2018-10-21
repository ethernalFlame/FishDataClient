package ui.family;

import client.FamilyDto;
import controller.family.FamilyCreateController;
import controller.family.FamilyDeleteController;
import controller.family.FamilyEditController;
import data.FamilyDao;
import ui.DatabaseView;
import ui.FileTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FamilyView extends JFrame {
    List<FamilyDto> familyDtos;
    JTable jTable;
    JButton deleteButton, createNew, editButton;
    JPanel buttonPanel;
    DatabaseView fileTableModel;
    private FamilyDao familyDao;

    public FamilyView(List<FamilyDto> familyDtos, FamilyDao familyDao, DatabaseView fileTableModel) {
        this.familyDao = familyDao;
        this.fileTableModel = fileTableModel;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);

        FamilyTableModel familyTableModel = new FamilyTableModel(familyDtos);
        jTable = new JTable(familyTableModel);
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
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = new JPopupMenu("lol");
                    popup.add(editButton);
                    popup.add(deleteButton);
                    System.out.println("kek");
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(new FamilyDeleteController(jTable, familyDao));
        editButton = new JButton("Редактировать");
        editButton.addActionListener(new FamilyEditController(familyDao, this, jTable));
        createNew = new JButton("Создать новую запись");
        createNew.addActionListener(new FamilyCreateController(familyDao, this));

        buttonPanel = new JPanel();
        buttonPanel.add(createNew);
        buttonPanel.setVisible(true);

        JScrollPane jScrollPane = new JScrollPane(jTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel dataPanel = new JPanel();
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        dataPanel.add(jScrollPane, BorderLayout.CENTER);
        dataPanel.add(jTable.getTableHeader(), BorderLayout.NORTH);
        add(jScrollPane);
        add(buttonPanel, BorderLayout.SOUTH);
        add(dataPanel, BorderLayout.NORTH);
        setVisible(true);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                fileTableModel.updateFishes();
                ((FileTableModel) fileTableModel.getjTable().getModel()).fireTableDataChanged();
            }
        });
    }

    public void updateFamily() {
        List<FamilyDto> all = familyDao.getAll();
        jTable.setModel(new FamilyTableModel(all));
        this.familyDtos = all;

        jTable.repaint();
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }
}
