package data;

import client.FishBean;
import client.PackageDto;
import client.ProcessingDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PackageDao {

    private static final String FROM_FISH_PACKAGE = "SELECT * FROM fish_package WHERE name='%s';";
    private static final String CREATE_FISH_PACKAGE = "INSERT INTO fish_package (name) values ('%s');";
    private static final String GET_BY_ID = "SELECT * FROM fish_package WHERE id=%d;";
    private static final String DELETE = "DELETE from fish_package where id=%d";
    private static final String GET_ALL = "SELECT * FROM fish_package;";
    private static final String UPDATE = "UPDATE fish_package SET name='%s' WHERE id=%d;";

    private Statement statement;

    public void update(PackageDto packageDto) {
        try {
            statement.execute(String.format(UPDATE, packageDto.getName(), packageDto.getId()));
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

    public List<PackageDto> getAll() {
        List<PackageDto> packageDtos = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                packageDtos.add(mapToPackageDto(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packageDtos;
    }

    private PackageDto mapToPackageDto(ResultSet resultSet) {
        try {
            return new PackageDto(resultSet.getLong("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PackageDao(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public long save(String packageName) throws Exception {
        statement.executeUpdate(String.format(CREATE_FISH_PACKAGE, packageName));
        return getByName(packageName).orElseThrow(() -> new Exception("no element!"));
    }

    public Optional<Long> getByName(String packageName) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(FROM_FISH_PACKAGE, packageName));
        if (resultSet.next()) {
            return Optional.of(resultSet.getLong("id"));
        } else return Optional.empty();
    }

    public PackageDto getById(long id) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format(GET_BY_ID, id));
            if (resultSet.next())
            return new PackageDto(resultSet.getLong("id"), resultSet.getString("name")) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new PackageDto(-1, "");
    }
}
