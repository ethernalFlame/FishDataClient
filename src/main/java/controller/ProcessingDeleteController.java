package controller;

import data.ProcessingDao;
import ui.ProcessingTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessingDeleteController implements ActionListener{
    private JTable jTable;
    private ProcessingDao processingDao;

    public ProcessingDeleteController(JTable jTable, ProcessingDao processingDao) {
        this.jTable = jTable;
        this.processingDao = processingDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        processingDao.delete((long)jTable.getValueAt(selectedRow, 0));

        ((ProcessingTableModel) jTable.getModel()).getProcessingDtos().remove(selectedRow);
        ((ProcessingTableModel) jTable.getModel()).fireTableDataChanged();

        jTable.repaint();
    }
}
