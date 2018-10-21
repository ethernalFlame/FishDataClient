package controller;

import data.FishDao;
import ui.DatabaseView;
import ui.FishCreateView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateController implements ActionListener {

    private FishDao fishDao;
    private DatabaseView databaseView;

    @Override
    public void actionPerformed(ActionEvent e) {
        new FishCreateView(fishDao, databaseView);
    }

    public CreateController(FishDao fishDao, DatabaseView databaseView) {
        this.fishDao = fishDao;
        this.databaseView = databaseView;
    }
}
