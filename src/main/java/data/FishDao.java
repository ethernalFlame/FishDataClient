package data;

import client.FishBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

public class FishDao {

    private static final String FROM = "SELECT * FROM fish WHERE name='%s' and type='%s' and value=%s and weight=%s and family_id=%d  and processing_id=%d  and fish_package_id=%d;";
    private static final String CREATE = "INSERT INTO fish (name, type, value, weight, family_id, fish_package_id, processing_id) values ('%s', '%s', '%s', '%s', '%d', '%d', '%d');";

    private Connection connection;
    private PackageDao packageDao;
    private AreolDao areolDao;
    private ProcessingDao processingDao;
    private FamilyDao familyDao;
    private NumberFormat numberInstance;
    private Locale locale;

    public FishDao() throws SQLException {
        this.connection = DbUtils.getDBConnection();
        this.packageDao = new PackageDao(connection);
        this.areolDao = new AreolDao(connection);
        this.processingDao = new ProcessingDao(connection);
        this.familyDao = new FamilyDao(connection);
        locale = Locale.ENGLISH;
        numberInstance = NumberFormat.getNumberInstance(locale);
        NumberFormat nf = numberInstance;
// for trailing zeros:
        nf.setMinimumFractionDigits(2);
// round to 2 digits:
        nf.setMaximumFractionDigits(2);
    }

    public void save(FishBean fishBean) throws Exception {
        try {
            Statement statement = connection.createStatement();

            Optional<Long> packageId = packageDao.getById(fishBean, statement);
            Optional<Long> areolId = areolDao.getById(fishBean, statement);
            Optional<Long> processingId = processingDao.getById(fishBean, statement);
            Optional<Long> familyId;
            if (areolId.isPresent()) {
                familyId = familyDao.getById(fishBean, statement, areolId.get());
            } else familyId = Optional.empty();

            boolean isUnique = false;
            if (!packageId.isPresent()) {
                packageId = Optional.of(packageDao.save(fishBean));
                isUnique = true;
            }
            if (!areolId.isPresent()) {
                areolId = Optional.of(areolDao.save(fishBean));
                isUnique = true;
            }
            if (!processingId.isPresent()) {
                processingId = Optional.of(processingDao.save(fishBean));
                isUnique = true;
            }
            if (!familyId.isPresent()) {
                familyId = Optional.of(familyDao.save(fishBean, areolId.get()));
                isUnique = true;
            }
            
            String value = numberInstance.format(fishBean.getValue());
            String weight = numberInstance.format(fishBean.getWeigh());

            if (!isUnique) {
                System.out.println(familyId.get());
                String format = String.format(FROM, fishBean.getName(), fishBean.getType(),value, weight, familyId.get(), processingId.get(), packageId.get());

                ResultSet resultSet = statement.executeQuery(format);
                if (!resultSet.next()){
                    isUnique = true;
                }
            }
            if (!isUnique) {
                statement.executeUpdate(String.format(CREATE, fishBean.getName(), fishBean.getType(), value, weight, familyId.get(), packageId.get(), processingId.get()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
