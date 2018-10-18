package client;

import data.DbUtils;
import data.FishDao;
import ui.DatabaseView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Main {

    private static FishDao fishDao;

    public static void main(String[] args) throws Exception {
        DbUtils.getDBConnection();
        fishDao = new FishDao();
        ArrayList<FishBean> all = fishDao.getAll();
        DatabaseView databaseView = new DatabaseView(all);
        fishDao.save(new FishBean(1, "o", "осетр", new FamilyBean("fam", "qqq"), "qw1", "qwe", 12.1, 122.1));
        System.out.println(Arrays.toString(all.toArray()));
    }
}
