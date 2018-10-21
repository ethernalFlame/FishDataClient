package controller.processing;

import client.ProcessingDto;
import data.ProcessingDao;
import ui.processing.ProcessingCreateView;
import ui.processing.ProcessingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessingCreateController implements ActionListener {

    private ProcessingDao processingDao;
    private ProcessingView processingView;

    public ProcessingCreateController(ProcessingDao processingDao, ProcessingView processingView) {
        this.processingDao = processingDao;
        this.processingView = processingView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ProcessingCreateView(processingDao, processingView);
    }
}
