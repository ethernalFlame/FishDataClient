package ui.areol;

import client.AreolDto;
import controller.areol.AreolCreateController;
import controller.areol.AreolDeleteController;
import controller.areol.AreolEditController;
import data.AreolDao;
import ui.DatabaseView;
import ui.FileTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class AreolView extends JFrame {
    List<AreolDto> areolDtos;
    JTable jTable;
    JButton deleteButton, createNew, editButton;
    JPanel buttonPanel;
    DatabaseView fileTableModel;
    private AreolDao areolDao;

    public AreolView(List<AreolDto> areolDtos, AreolDao areolDao, DatabaseView fileTableModel) {
        this.areolDao = areolDao;
        this.fileTableModel = fileTableModel;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);

        AreolTableModel areolTableModel = new AreolTableModel(areolDtos);
        jTable = new JTable(areolTableModel);
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
        deleteButton.addActionListener(new AreolDeleteController(jTable, areolDao));
        editButton = new JButton("Редактировать");
        editButton.addActionListener(new AreolEditController(areolDao, this, jTable));
        createNew = new JButton("Создать новую запись");
        createNew.addActionListener(new AreolCreateController(areolDao, this));

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

    public void updatePackage() {
        List<AreolDto> all = areolDao.getAll();
        jTable.setModel(new AreolTableModel(all));
        this.areolDtos = all;

        jTable.repaint();
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

}
