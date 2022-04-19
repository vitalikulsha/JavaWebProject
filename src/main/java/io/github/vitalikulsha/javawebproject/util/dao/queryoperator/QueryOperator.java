package io.github.vitalikulsha.javawebproject.util.dao.queryoperator;

import io.github.vitalikulsha.javawebproject.config.ConnectionSource;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapper;
import io.github.vitalikulsha.javawebproject.exception.ConnectionException;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Query operator interface. Provides methods to execute various types of database queries.
 *
 * @param <T> entity type in this query operator
 */
@Slf4j
public class QueryOperator<T> {
    private static final String ERROR_CONNECT_DB = "ConnectionException when trying to connect to DB.";
    private static final String ERROR_SQL_QUERY = "SQLException while executing SQL query query: %s.";
    private static final String INFO_SQL_QUERY = "SQL query: %s.";

    private final ConnectionSource connectionSource = ConnectionSource.instance();
    private final RowMapper<T> mapper;

    public QueryOperator(RowMapper<T> mapper) {
        this.mapper = mapper;
    }

    /**
     * Executes a select query with additional PreparedStatement parameter.
     *
     * @param sqlQuery SQL query
     * @param params   PreparedStatement parameters SQL query
     * @return list of entities
     * @throws DaoException thrown when an SQL exception occurs
     */
    public List<T> executeEntityListQuery(String sqlQuery, Object... params) throws DaoException {
        log.info(String.format(INFO_SQL_QUERY, sqlQuery));
        List<T> result = new ArrayList<>();
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            setStatementParam(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = mapper.getEntity(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            log.error(String.format(ERROR_SQL_QUERY, sqlQuery));
            throw new DaoException(String.format(ERROR_SQL_QUERY, sqlQuery), e);
        } catch (ConnectionException e) {
            log.error(ERROR_CONNECT_DB);
            throw new DaoException(ERROR_CONNECT_DB, e);
        }
        return result;
    }

    /**
     * Executes single entity select query with additional PreparedStatement parameter.
     *
     * @param sqlQuery SQL query
     * @param params   PreparedStatement parameters SQL query
     * @return entity type
     * @throws DaoException thrown when an SQL exception occurs
     */
    public T executeSingleEntityQuery(String sqlQuery, Object... params) throws DaoException {
        log.info(String.format(INFO_SQL_QUERY, sqlQuery));
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            setStatementParam(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? mapper.getEntity(resultSet) : null;
        } catch (SQLException e) {
            log.error(String.format(ERROR_SQL_QUERY, sqlQuery));
            throw new DaoException(String.format(ERROR_SQL_QUERY, sqlQuery), e);
        } catch (ConnectionException e) {
            log.error(ERROR_CONNECT_DB);
            throw new DaoException(ERROR_CONNECT_DB, e);
        }
    }

    /**
     * Executes update query with additional PreparedStatement parameters.
     *
     * @param sqlQuery SQL query
     * @param params   PreparedStatement parameters SQL query
     * @return database query result
     * @throws DaoException thrown when an SQL exception occurs
     */
    public int executeUpdate(String sqlQuery, Object... params) throws DaoException {
        log.info(String.format(INFO_SQL_QUERY, sqlQuery));
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            setStatementParam(preparedStatement, params);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(String.format(ERROR_SQL_QUERY, sqlQuery));
            throw new DaoException(String.format(ERROR_SQL_QUERY, sqlQuery), e);
        } catch (ConnectionException e) {
            log.error(ERROR_CONNECT_DB);
            throw new DaoException(ERROR_CONNECT_DB, e);
        }
    }

    /**
     * Executes a query to count the number of records with additional PreparedStatement parameters.
     *
     * @param sqlQuery SQL query
     * @param params   PreparedStatement parameters SQL query
     * @return number of records found
     * @throws DaoException thrown when an SQL exception occurs
     */
    public int executeCountQuery(String sqlQuery, Object... params) throws DaoException {
        log.info(String.format(INFO_SQL_QUERY, sqlQuery));
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            setStatementParam(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            log.error(String.format(ERROR_SQL_QUERY, sqlQuery));
            throw new DaoException(String.format(ERROR_SQL_QUERY, sqlQuery), e);
        } catch (ConnectionException e) {
            log.error(ERROR_CONNECT_DB);
            throw new DaoException(ERROR_CONNECT_DB, e);
        }
    }

    private void setStatementParam(PreparedStatement preparedStatement, Object... params) throws SQLException {
        if (params == null) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }
}
