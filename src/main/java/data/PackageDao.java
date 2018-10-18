package data;

import client.FishBean;
import client.PackageDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class PackageDao {

    private static final String FROM_FISH_PACKAGE = "SELECT * FROM fish_package WHERE name='%s';";
    private static final String CREATE_FISH_PACKAGE = "INSERT INTO fish_package (name) values ('%s');";
    private static final String GET_BY_ID = "SELECT * FROM fish_package WHERE id=%d;";
    private Statement statement;

    public PackageDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(FishBean fishBean) throws Exception {
        statement.executeUpdate(String.format(CREATE_FISH_PACKAGE, fishBean.getPack()));
        return getByName(fishBean).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getByName(FishBean fishBean) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_FISH_PACKAGE, fishBean.getPack()));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }

    public PackageDto getById(long id) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format(GET_BY_ID, id));
            resultSet.next();
            return new PackageDto(resultSet.getLong("id"), resultSet.getString("name")) ;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
