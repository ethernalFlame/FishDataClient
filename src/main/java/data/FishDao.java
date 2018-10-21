package data;

import client.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

public class FishDao {

    private static final String FROM = "SELECT * FROM fish WHERE name='%s' and type='%s' and family_id=%d  and processing_id=%d  and fish_package_id=%d;";
    private static final String CREATE = "INSERT INTO fish (name, type, value, weight, family_id, fish_package_id, processing_id) values ('%s', '%s', '%s', '%s', '%d', '%d', '%d');";
    private static final String GET_ALL = "SELECT * FROM fish;";
    private static final String DELETE = "DELETE FROM fish WHERE id=%d";
    private static final String UPDATE = "UPDATE fish SET name='%s', type='%s', family_id=%d, fish_package_id=%d, processing_id=%d, weight='%s', value ='%s' WHERE id=%d";

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
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(1);
    }

    public void update(FishBean fishBean) {
        try {
            Statement statement = connection.createStatement();
            Optional<Long> areolDaoByName = areolDao.getByName(fishBean);
            if (!areolDaoByName.isPresent()) {
                areolDaoByName = Optional.of(areolDao.save(fishBean));
            }
            Optional<Long> familyDaoByName = familyDao.getByName(fishBean, statement, areolDaoByName.get());
            if (!familyDaoByName.isPresent()) {
                familyDaoByName = Optional.of(familyDao.save(fishBean, areolDaoByName.get()));
            }
            Optional<Long> packageDaoByName = packageDao.getByName(fishBean);
            if (!packageDaoByName.isPresent()) {
                packageDaoByName = Optional.of(packageDao.save(fishBean));
            }
            Optional<Long> processingDaoByName = processingDao.getByName(fishBean);
            if (!processingDaoByName.isPresent()) {
                processingDaoByName = Optional.of(processingDao.save(fishBean));
            }
            statement.execute(String.format(UPDATE, fishBean.getName(), fishBean.getType(), familyDaoByName.get(), packageDaoByName.get(),
                    processingDaoByName.get(), fishBean.getWeigh(), fishBean.getValue(), fishBean.getId()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(FishBean fishBean) throws Exception {
        try {
            Statement statement = connection.createStatement();

            Optional<Long> packageId = packageDao.getByName(fishBean);
            Optional<Long> areolId = areolDao.getByName(fishBean);
            Optional<Long> processingId = processingDao.getByName(fishBean);
            Optional<Long> familyId;
            if (areolId.isPresent()) {
                familyId = familyDao.getByName(fishBean, statement, areolId.get());
            } else familyId = Optional.empty();

            int uniqueCount = 0;
            if (!packageId.isPresent()) {
                packageId = Optional.of(packageDao.save(fishBean));
                uniqueCount++;
            }
            if (!areolId.isPresent()) {
                uniqueCount++;
                areolId = Optional.of(areolDao.save(fishBean));
            }
            if (!processingId.isPresent()) {
                uniqueCount++;
                processingId = Optional.of(processingDao.save(fishBean));
            }
            if (!familyId.isPresent()) {
                uniqueCount++;
                familyId = Optional.of(familyDao.save(fishBean, areolId.get()));
            }

            String value = numberInstance.format(fishBean.getValue());
            String weight = numberInstance.format(fishBean.getWeigh());


            String format = String.format(FROM, fishBean.getName(), fishBean.getType(), familyId.get(), processingId.get(), packageId.get());

            ResultSet resultSet = statement.executeQuery(format);

            if (resultSet.next()) {
                if (resultSet.getDouble("value") != fishBean.getValue() || resultSet.getDouble("weight") != fishBean.getWeigh()) {
                    uniqueCount++;
                }
            }

            if (uniqueCount > 0) {
                statement.executeUpdate(String.format(CREATE, fishBean.getName(), fishBean.getType(), value, weight, familyId.get(), packageId.get(), processingId.get()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FishBean> getAll() {
        ArrayList<FishBean> fishBeans = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                fishBeans.add(mapToFishBean(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fishBeans;
    }

    public void delete(long id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format(DELETE, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private FishBean mapToFishBean(ResultSet resultSet) {
        try {
            FamilyDto familyDto = familyDao.getById(resultSet.getLong("family_id"));
            AreolDto areol = areolDao.getById(familyDto.getAreolId());
            PackageDto packageDto = packageDao.getById(resultSet.getLong("fish_package_id"));
            FamilyBean familyBean = new FamilyBean(familyDto.getName(), areol.getName());
            ProcessingDto processingDto = processingDao.getById(resultSet.getLong("processing_id"));
            return new FishBean(resultSet.getLong("id"), resultSet.getString("type"), resultSet.getString("name"),
                    familyBean, packageDto.getName(), processingDto.getName(), resultSet.getDouble("weight"), resultSet.getDouble("value"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFROM() {
        return FROM;
    }

    public static String getCREATE() {
        return CREATE;
    }

    public static String getGetAll() {
        return GET_ALL;
    }

    public static String getDELETE() {
        return DELETE;
    }

    public static String getUPDATE() {
        return UPDATE;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PackageDao getPackageDao() {
        return packageDao;
    }

    public void setPackageDao(PackageDao packageDao) {
        this.packageDao = packageDao;
    }

    public AreolDao getAreolDao() {
        return areolDao;
    }

    public void setAreolDao(AreolDao areolDao) {
        this.areolDao = areolDao;
    }

    public ProcessingDao getProcessingDao() {
        return processingDao;
    }

    public void setProcessingDao(ProcessingDao processingDao) {
        this.processingDao = processingDao;
    }

    public FamilyDao getFamilyDao() {
        return familyDao;
    }

    public void setFamilyDao(FamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    public NumberFormat getNumberInstance() {
        return numberInstance;
    }

    public void setNumberInstance(NumberFormat numberInstance) {
        this.numberInstance = numberInstance;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
