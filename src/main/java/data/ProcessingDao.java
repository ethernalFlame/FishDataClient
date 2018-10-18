package data;

import client.FishBean;
import client.ProcessingDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ProcessingDao {

    private static final String FROM_PROCESSING = "SELECT * FROM processing WHERE name='%s';";
    private static final String CREATE_PROCESSING = "INSERT INTO processing (name) values ('%s');";
    private static final String GET_BY_ID = "SELECT * FROM processing WHERE id=%d;";

    private Statement statement;

    public ProcessingDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(FishBean fishBean) throws Exception {
        statement.executeUpdate(String.format(CREATE_PROCESSING, fishBean.getProcessing()));
        return getByName(fishBean).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getByName(FishBean fishBean) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_PROCESSING, fishBean.getProcessing()));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }

    public ProcessingDto getById(long id) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format(GET_BY_ID, id));
            resultSet.next();
            return new ProcessingDto(resultSet.getLong("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
