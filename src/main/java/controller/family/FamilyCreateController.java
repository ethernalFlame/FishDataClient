package controller.family;

import data.FamilyDao;
import ui.family.FamilyCreateView;
import ui.family.FamilyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FamilyCreateController implements ActionListener {

    private FamilyDao familyDao;
    private FamilyView familyView;

    public FamilyCreateController(FamilyDao familyDao, FamilyView familyView) {
        this.familyDao = familyDao;
        this.familyView = familyView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new FamilyCreateView(familyDao, familyView);
    }
}
