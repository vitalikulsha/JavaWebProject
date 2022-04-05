package io.github.vitalikulsha.javawebproject.config;

import io.github.vitalikulsha.javawebproject.exception.ConnectionException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for connecting to a database
 */
@Slf4j
public class ConnectionSource {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static final String DB_URL = "jdbc:hsqldb:mem:libraryDb";

    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "sa";

    private static final ConnectionSource instance = new ConnectionSource();

    public static ConnectionSource instance() {
        return instance;
    }

    public Connection createConnection() throws ConnectionException {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            log.error("Unable to get connection.", e);
            throw new ConnectionException("SQLException in createConnection() method.", e);
        }
    }

    private ConnectionSource() {
        try {
            DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                try (Statement statement = conn.createStatement()) {
                    statement.execute(getSql("init-ddl.sql"));
                }
                try (Statement statement = conn.createStatement()) {
                    statement.execute(getSql("init-dml.sql"));
                }
            }
        } catch (SQLException e) {
            log.error("Failed to initialize connection", e);
            throw new RuntimeException();
        }
    }

    private static String getSql(final String resourceName) {
        return new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                ConnectionSource.class.getClassLoader().getResourceAsStream(resourceName))))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}