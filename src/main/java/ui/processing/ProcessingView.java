package ui.processing;

import client.ProcessingDto;
import controller.processing.ProcessingCreateController;
import controller.processing.ProcessingDeleteController;
import controller.processing.ProcessingEditController;
import data.ProcessingDao;
import ui.DatabaseView;
import ui.FileTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ProcessingView extends JFrame {
    List<ProcessingDto> processingDtos;
    JTable jTable;
    JButton deleteButton, createNew, editButton;
    JPanel buttonPanel;
    DatabaseView fileTableModel;
    private ProcessingDao processingDao;

    public ProcessingView(List<ProcessingDto> processingDtos, ProcessingDao processingDao, DatabaseView fileTableModel) {
        this.processingDao = processingDao;
        this.fileTableModel = fileTableModel;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);

        ProcessingTableModel processingTableModel = new ProcessingTableModel(processingDtos);
        jTable = new JTable(processingTableModel);
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
        deleteButton.addActionListener(new ProcessingDeleteController(jTable, processingDao));
        editButton = new JButton("Редактировать");
        editButton.addActionListener(new ProcessingEditController(processingDao, this, jTable));
        createNew = new JButton("Создать новую запись");
        createNew.addActionListener(new ProcessingCreateController(processingDao, this));

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

    public void updateProcessing() {
        List<ProcessingDto> all = processingDao.getAll();
        jTable.setModel(new ProcessingTableModel(all));
        this.processingDtos = all;

        //
        jTable.repaint();
    }

    public List<ProcessingDto> getProcessing() {
        return processingDtos;
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
}
