package ui.processing;

import client.ProcessingDto;
import data.ProcessingDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProcessingEditView extends JFrame {
    private final JButton jButton;
    private final JButton jButton1;
    private ProcessingDto processingDto;
    private JLabel jLabel;
    private JTextField jTextField;
    private ProcessingDao processingDao;
    private ProcessingView processingView;

    public ProcessingEditView(ProcessingDto processingDto, ProcessingDao processingDao, ProcessingView processingTableModel) {
        this.processingDao = processingDao;
        this.processingView= processingTableModel;
        this.processingDto = processingDto;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(0, 2));
        initLabels(processingDto);
        setVisible(true);
        jButton = new JButton("Очистить");
        jButton.addActionListener(e -> clear(processingDto));
        add(jButton);
        jButton1 = new JButton("Подтвердить");
        jButton1.addActionListener(e -> {
            this.processingDao.update(mapToProcessing());
            processingView.updateProcessing();
        });
        add(jButton1);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                processingView.updateProcessing();
                ((ProcessingTableModel) processingView.getjTable().getModel()).fireTableDataChanged();
            }
        });
    }

    private ProcessingDto mapToProcessing() {
        return new ProcessingDto(this.processingDto.getId(), jTextField.getText());
    }

    public void initLabels(ProcessingDto processingDto) {
        jLabel = new JLabel("Название");
        jTextField = new JTextField(processingDto.getName());
        add(jLabel);
        add(jTextField);
    }

    private void clear(ProcessingDto processingDto) {
        jTextField.setText(processingDto.getName());
    }
}
