package io.github.vitalikulsha.JavaWebProject.dao.query;

import io.github.vitalikulsha.JavaWebProject.config.ConnectionSource;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapper;
import io.github.vitalikulsha.JavaWebProject.exception.ConnectionException;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class QueryOperator<T> {
    private final ConnectionSource connectionSource = ConnectionSource.instance();
    private final RowMapper<T> mapper;

    public QueryOperator(RowMapper<T> mapper) {
        this.mapper = mapper;
    }

    public List<T> executeEntityListQueryWithoutParam(String sqlQuery) throws DaoException {
        log.info("SQL query: " + sqlQuery);
        List<T> result = new ArrayList<>();
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.add(mapper.getEntity(resultSet));
            }
        } catch (SQLException e) {
            log.error("Unable to execute select query.", e);
            throw new DaoException("SQLException while executing a select query.", e);
        } catch (ConnectionException e) {
            log.error("Unable to get connection.", e);
            throw new DaoException("Unable to get connection", e);
        }
        return result;
    }

    public List<T> executeEntityListQueryWithLikeParam(String sqlQuery, String param) throws DaoException {
        log.info("SQL query: " + sqlQuery);
        String query = String.format(sqlQuery, param);
        return executeEntityListQueryWithoutParam(query);
    }

    public List<T> executeEntityListQueryWithParam(String sqlQuery, Object param) throws DaoException {
        log.info("SQL query: " + sqlQuery);
        List<T> result = new ArrayList<>();
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setObject(1, param);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = mapper.getEntity(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            log.error("Unable to execute select query.", e);
            throw new DaoException("SQLException while executing a select query.", e);
        } catch (ConnectionException e) {
            log.error("Unable to get connection.", e);
            throw new DaoException("Unable to get connection", e);
        }
        return result;
    }

    public T executeSingleEntityQuery(String sqlQuery, Object param) throws DaoException {
        log.info("SQL query: " + sqlQuery);
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setObject(1, param);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapper.getEntity(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error("Unable to execute select query.", e);
            throw new DaoException("SQLException while executing a select query.", e);
        } catch (ConnectionException e) {
            log.error("Unable to get connection.", e);
            throw new DaoException("Unable to get connection", e);
        }
    }

    public int executeUpdate(String sqlQuery, Object... params) throws DaoException {
        log.info("SQL query: " + sqlQuery);
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            setStatementParam(preparedStatement, params);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to execute update query.", e);
            throw new DaoException("SQLException while executing an update query.", e);
        } catch (ConnectionException e) {
            log.error("Unable to get connection.", e);
            throw new DaoException("Unable to get connection", e);
        }
    }

    private void setStatementParam(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }
}
