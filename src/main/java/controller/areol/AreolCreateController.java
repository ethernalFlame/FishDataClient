package controller.areol;

import data.AreolDao;
import ui.areol.AreolCreateView;
import ui.areol.AreolView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreolCreateController implements ActionListener {

    private AreolDao areolDao;
    private AreolView areolView;

    public AreolCreateController(AreolDao areolDao, AreolView areolView) {
        this.areolDao = areolDao;
        this.areolView = areolView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new AreolCreateView(areolDao, areolView);
    }
}
