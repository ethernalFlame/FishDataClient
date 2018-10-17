package data;

import client.FishBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class FamilyDao {

    private static final String FROM_FAMILY = "SELECT * FROM family WHERE name='%s' and areol_id=%d;";
    private static final String CREATE_FAMILY = "INSERT INTO family (name, areol_id) values ('%s', %d);";

    private Statement statement;

    public FamilyDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(FishBean fishBean, long areolId) throws Exception {
        statement.executeUpdate(String.format(CREATE_FAMILY, fishBean.getFamily().getName(), areolId));
        return getById(fishBean, statement, areolId).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getById(FishBean fishBean, Statement statement, long areolId) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_FAMILY, fishBean.getFamily().getName(), areolId));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }
}
