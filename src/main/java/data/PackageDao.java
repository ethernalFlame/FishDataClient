package data;

import client.FishBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class PackageDao {

    private static final String FROM_FISH_PACKAGE = "SELECT * FROM fish_package WHERE name='%s';";
    private static final String CREATE_FISH_PACKAGE = "INSERT INTO fish_package (name) values ('%s');";
    private Statement statement;

    public PackageDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(FishBean fishBean) throws Exception {
        statement.executeUpdate(String.format(CREATE_FISH_PACKAGE, fishBean.getPack()));
        return getById(fishBean, statement).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getById(FishBean fishBean, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_FISH_PACKAGE, fishBean.getPack()));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }
}
