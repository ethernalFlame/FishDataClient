package ui.processing;

import client.ProcessingDto;
import data.ProcessingDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProcessingCreateView extends JFrame {
    private final JButton jButton;
    private final JButton jButton1;

    private JLabel jLabel;
    private JTextField jTextField;
    private ProcessingDao processingDao;
    private ProcessingView processingView;

    public ProcessingCreateView (ProcessingDao processingDao, ProcessingView processingTableModel) {
        this.processingDao = processingDao;
        this.processingView= processingTableModel;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(0, 2));
        setVisible(true);
        jButton = new JButton("Очистить");
        jButton.addActionListener(e -> clear());
       initLabels();
        add(jButton);
        jButton1 = new JButton("Подтвердить");
        jButton1.addActionListener(e -> {
            try {
                this.processingDao.save(this.jTextField.getText());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
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

    public void initLabels() {
        jLabel = new JLabel("Название");
        jTextField = new JTextField("");
        add(jLabel);
        add(jTextField);
    }

    private void clear() {
        jTextField.setText("");
    }
}
