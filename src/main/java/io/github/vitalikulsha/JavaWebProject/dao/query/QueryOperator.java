package io.github.vitalikulsha.JavaWebProject.dao.query;

import io.github.vitalikulsha.JavaWebProject.config.ConnectionSource;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapper;
import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.RecordBook;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class QueryOperator<T> {
    private final ConnectionSource connectionSource = ConnectionSource.instance();
    private final RowMapper<T> mapper;

    public QueryOperator(RowMapper<T> mapper) {
        this.mapper = mapper;
    }

    public List<T> executeSqlQuery(String sqlQuery, String... param) {
        List<T> result = new ArrayList<>();
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(completeSqlQuery(sqlQuery, param));
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.add(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public T executeSingleEntityQuery(String sqlQuery, Object param) {
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(sqlQuery, param));
             ResultSet resultSet = preparedStatement.executeQuery()) {
            System.out.println("query: " + String.format(sqlQuery, param));
            if (resultSet.next()) {
                return mapper.map(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int executeMaxIdQuery(String sqlQuery) {
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String completeSqlQuery(String sqlQuery, String... param) {
        List<String> params = new ArrayList<>();
        Pattern pattern = Pattern.compile("%s%");
        Matcher matcher = pattern.matcher(sqlQuery);
        while (matcher.find()) {
            params.add(param[0]);
        }
        return String.format(sqlQuery, params.toArray());
    }
}
