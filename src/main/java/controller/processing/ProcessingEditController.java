package controller.processing;

import client.ProcessingDto;
import data.ProcessingDao;
import ui.processing.ProcessingEditView;
import ui.processing.ProcessingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessingEditController implements ActionListener{
    private ProcessingDao processingDao;
    private ProcessingView processingView;
    private JTable jTable;

    public ProcessingEditController(ProcessingDao processingDao, ProcessingView processingView, JTable jTable) {
        this.processingDao = processingDao;
        this.processingView = processingView;
        this.jTable = jTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = jTable.getSelectedRow();
        ProcessingDto processingDto = mapToProcessingDto(selectedRow);
        new ProcessingEditView(processingDto, processingDao, processingView);
    }

    private ProcessingDto mapToProcessingDto(int selectedRow) {
        return new ProcessingDto((long)jTable.getValueAt(selectedRow, 0), (String)jTable.getValueAt(selectedRow, 1));
    }
}
