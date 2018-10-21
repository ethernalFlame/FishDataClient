package data;

import client.AreolDto;
import client.FamilyDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FamilyDao {

    private static final String FROM_FAMILY = "SELECT * FROM family WHERE name='%s' and areol_id=%d;";
    private static final String GET_BY_ID = "SELECT * FROM family WHERE id=%d;";
    private static final String CREATE_FAMILY = "INSERT INTO family (name, areol_id) values ('%s', %d);";
    private static final String DELETE = "DELETE from family where id=%d";
    private static final String GET_ALL = "SELECT * FROM family;";
    private static final String UPDATE = "UPDATE family SET name='%s' WHERE id=%d;";

    private Statement statement;
    private FamilyDto familyDto;
    private AreolDao areolDao;

    public FamilyDao(Connection connection, AreolDao areolDao) throws SQLException {
        this.statement = connection.createStatement();
        this.areolDao = areolDao;
    }

    public void update(FamilyDto familyDto) {
        try {
            areolDao.update(new AreolDto(familyDto.getAreolId(), familyDto.getAreolName()));
            statement.execute(String.format(UPDATE, familyDto.getName(), familyDto.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id, long areolId) {
        try {
            areolDao.delete(areolId);
            statement.execute(String.format(DELETE, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FamilyDto> getAll() {
        List<FamilyDto> familyDtos = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                familyDtos.add(mapToFamilyDto(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return familyDtos;
    }

    private FamilyDto mapToFamilyDto(ResultSet resultSet) {
        try {
            return new FamilyDto(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getLong("areol_id"), areolDao.getById(resultSet.getLong("areol_id")).getName());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long save(String family, String areol) throws Exception {
        long save = areolDao.save(areol);
        statement.executeUpdate(String.format(CREATE_FAMILY, family, save));
        return getByName(family, statement, save).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getByName(String family, Statement statement, long areolId) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_FAMILY, family, areolId));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }

    public FamilyDto getById(long id) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format(GET_BY_ID, id));
            resultSet.next();
            return new FamilyDto(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getLong("areol_id"), areolDao.getById(resultSet.getLong("areol_id")).getName());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
