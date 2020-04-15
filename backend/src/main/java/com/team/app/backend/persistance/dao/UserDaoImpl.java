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
                "INSERT INTO users(username, email, password, STATUS) VALUES ( ?, ?, ?, ? )",
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.isEnabled());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE USERS set USERNAME = ?, EMAIL = ?, PASSWORD = ?, STATUS = ? where id = ?",
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
    public User findByEmail(String email) {
        return jdbcTemplate.query(
                "select * from users where email = '" + email + "'",
                resultSet -> {
                    if (resultSet.next()) {
                        return new User(
                                (long) resultSet.getInt("id"),
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
}
