package data;

import client.AreolDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AreolDao {

    private static final String FROM_AREOL = "SELECT * FROM areol WHERE name='%s';";
    private static final String CREATE_AREOL = "INSERT INTO areol (name) values ('%s');";
    private static final String GET_BY_ID = "SELECT * FROM areol WHERE id=%d;";
    private static final String DELETE = "DELETE from areol where id=%d";
    private static final String GET_ALL = "SELECT * FROM areol;";
    private static final String UPDATE = "UPDATE areol SET name='%s' WHERE id=%d;";
    private Statement statement;

    public void update(AreolDto areolDto) {
        try {
            statement.execute(String.format(UPDATE, areolDto.getName(), areolDto.getId()));
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

    public List<AreolDto> getAll() {
        List<AreolDto> areolDtos = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                areolDtos.add(mapToAreolDto(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areolDtos;
    }

    private AreolDto mapToAreolDto(ResultSet resultSet) {
        try {
            return new AreolDto(resultSet.getLong("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AreolDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(String areol) throws Exception {
        statement.executeUpdate(String.format(CREATE_AREOL, areol));
        return getByName(areol).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getByName(String areol) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_AREOL, areol));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }

    public AreolDto getById(long id) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format(GET_BY_ID, id));
            if (resultSet.next())
            return new AreolDto(resultSet.getLong("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new AreolDto(-1, "");
    }
}
