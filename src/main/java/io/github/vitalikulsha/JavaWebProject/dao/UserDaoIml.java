package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.domain.User;
import io.github.vitalikulsha.JavaWebProject.config.ConnectionSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoIml implements UserDao {
    private final ConnectionSource connectionSource = ConnectionSource.instance();

    @Override
    public Optional<User> getById(Integer id) {
        String sqlQuery = "SELECT * FROM user WHERE user_id=?";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(getUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        String sqlQuery = "SELECT * FROM user WHERE login=?";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(getUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public boolean isExist(String login, String password) {
        Optional<User> user = getByLogin(login);
        if (user.isEmpty()) {
            return false;
        } else return user.get().getLogin().equals(login)
                && user.get().getPassword().equals(password);
    }

    private User getUser(ResultSet resultSet) {
        try {
            Integer id = resultSet.getInt("user_id");
            String username = resultSet.getString("login");
            String password = resultSet.getString("password");
            User.Role role = User.Role.valueOf(resultSet.getString("role"));
            return new User(id, username, password, role);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
