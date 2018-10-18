package data;

import client.AreolDto;
import client.FishBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class AreolDao {

    private static final String FROM_AREOL = "SELECT * FROM areol WHERE name='%s';";
    private static final String CREATE_AREOL = "INSERT INTO areol (name) values ('%s');";
    private static final String GET_BY_ID = "SELECT * FROM areol WHERE id=%d;";
    private Statement statement;

    public AreolDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(FishBean fishBean) throws Exception {
        statement.executeUpdate(String.format(CREATE_AREOL, fishBean.getFamily().getAreol()));
        return getByName(fishBean).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getByName(FishBean fishBean) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_AREOL, fishBean.getFamily().getAreol()));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }

    public AreolDto getById(long id) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format(GET_BY_ID, id));
            resultSet.next();
            return new AreolDto(resultSet.getLong("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
