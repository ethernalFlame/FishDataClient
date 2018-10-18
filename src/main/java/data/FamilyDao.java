package data;

import client.FamilyBean;
import client.FamilyDto;
import client.FishBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class FamilyDao {

    private static final String FROM_FAMILY = "SELECT * FROM family WHERE name='%s' and areol_id=%d;";
    private static final String GET_BY_ID = "SELECT * FROM family WHERE id=%d;";
    private static final String CREATE_FAMILY = "INSERT INTO family (name, areol_id) values ('%s', %d);";

    private Statement statement;

    public FamilyDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(FishBean fishBean, long areolId) throws Exception {
        statement.executeUpdate(String.format(CREATE_FAMILY, fishBean.getFamily().getName(), areolId));
        return getByName(fishBean, statement, areolId).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getByName(FishBean fishBean, Statement statement, long areolId) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_FAMILY, fishBean.getFamily().getName(), areolId));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }

    public FamilyDto getById(long id){
        try {
            ResultSet resultSet = statement.executeQuery(String.format(GET_BY_ID, id));
            resultSet.next();
            return new FamilyDto(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getLong("areol_id"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
