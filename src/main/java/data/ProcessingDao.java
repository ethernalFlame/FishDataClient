package data;

import client.FishBean;
import client.ProcessingDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProcessingDao {

    private static final String FROM_PROCESSING = "SELECT * FROM processing WHERE name='%s';";
    private static final String CREATE_PROCESSING = "INSERT INTO processing (name) values ('%s');";
    private static final String GET_BY_ID = "SELECT * FROM processing WHERE id=%d;";
    private static final String DELETE = "DELETE from processing where id=%d";
    private static final String GET_ALL = "SELECT * FROM processing;";
    private static final String UPDATE = "UPDATE processing SET name='%s' WHERE id=%d;";

    private Statement statement;

    public ProcessingDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(String processing) throws Exception {
        statement.executeUpdate(String.format(CREATE_PROCESSING, processing));
        return getByName(processing).orElseThrow(() -> new Exception("no element!"));
    }

    public void update(ProcessingDto processingDto) {
        try {
            statement.execute(String.format(UPDATE, processingDto.getName(), processingDto.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try {
            statement.execute(String.format(DELETE, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProcessingDto> getAll() {
        List<ProcessingDto> processingDtos = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                processingDtos.add(mapToProcessingDto(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return processingDtos;
    }

    private ProcessingDto mapToProcessingDto(ResultSet resultSet) {
        try {
            return new ProcessingDto(resultSet.getLong("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<Long> getByName(String processing) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_PROCESSING, processing));
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
