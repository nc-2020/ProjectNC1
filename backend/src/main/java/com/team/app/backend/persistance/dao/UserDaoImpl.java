package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(
                "INSERT INTO users(firstname, lastname, username, email, password, STATUS) VALUES ( ?, ?, ?, ?, ?, ? )",
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.isEnabled());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE USERS set firstname = ?, lastname = ?, USERNAME = ?, EMAIL = ?, PASSWORD = ?, STATUS = ? where id = ?",
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.getId()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE from USERS where id = ?",
                id
        );
    }

    @Override
    public User get(Long id) {
        return jdbcTemplate.query(
                "select * from users where id = " + id.toString(),
                resultSet -> {
                    if (resultSet.next()) {
                        return new User(
                                (long) resultSet.getInt("id"),
                                resultSet.getString("firstname"),
                                resultSet.getString("lastname"),
                                resultSet.getString("username"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getBoolean("status")
                        );
                    } else {
                        return null;
                    }
                }
        );
    }

    @Override
    public User findByUsername(String username) {
        return jdbcTemplate.query(
                "select * from users where USERNAME = '" + username + "'",
                resultSet -> {
                    if (resultSet.next()) {
                        return new User(
                                (long) resultSet.getInt("id"),
                                resultSet.getString("firstname"),
                                resultSet.getString("lastname"),
                                resultSet.getString("username"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getBoolean("status")
                        );
                    } else {
                        return null;
                    }
                }
        );
    }

    @Override
    public String getUserPasswordByUsername(String username) {
        return jdbcTemplate.query(
                "select PASSWORD from users where USERNAME = '" + username + "'",
                resultSet -> {
                    if (resultSet.next()) {
                        return resultSet.getString("password");
                    } else {
                        return null;
                    }
                }
        );
    }
}
