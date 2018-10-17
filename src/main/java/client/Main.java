package client;

import data.DbUtils;
import data.FishDao;
import ui.DatabaseView;


public class Main {

    private static FishDao fishDao;

    public static void main(String[] args) throws Exception {
        DbUtils.getDBConnection();
        DatabaseView databaseView = new DatabaseView();
        fishDao = new FishDao();
        fishDao.save(new FishBean(1, "o", "осетр", new FamilyBean("fam", "qqq"), "qw1", "qwe", 12.1, 122.1));
    }
}
