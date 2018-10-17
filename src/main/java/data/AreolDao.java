package data;

import client.FishBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class AreolDao {

    private static final String FROM_AREOL = "SELECT * FROM areol WHERE name='%s';";
    private static final String CREATE_AREOL = "INSERT INTO areol (name) values ('%s');";
    private Statement statement;

    public AreolDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(FishBean fishBean) throws Exception {
        statement.executeUpdate(String.format(CREATE_AREOL, fishBean.getFamily().getAreol()));
        return getById(fishBean, statement).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getById(FishBean fishBean, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_AREOL, fishBean.getFamily().getAreol()));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }
}
