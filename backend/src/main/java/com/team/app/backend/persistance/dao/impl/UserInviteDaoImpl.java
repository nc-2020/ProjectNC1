package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.UserInviteDao;
import com.team.app.backend.persistance.dao.mappers.UserInviteRowMapper;
import com.team.app.backend.persistance.model.UserInvite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;


@Component
public class UserInviteDaoImpl implements UserInviteDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserInviteRowMapper userInviteRowMapper = new UserInviteRowMapper();

    public UserInviteDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void send(UserInvite userInvite) {
        jdbcTemplate.update(
                "INSERT INTO friend_to(activated, invite_text, invite_date, user_id_from, user_id_to) VALUES (?, ?, ?, ?, ?)",
                userInvite.isActivated(),
                userInvite.getInviteText(),
                userInvite.getInviteDate(),
                userInvite.getUserIdFrom(),
                userInvite.getUserIdTo());
    }

    @Override
    public List<UserInvite> getUserInvite(Long userId) {
        return jdbcTemplate.query("SELECT F.id, F.invite_text, U.username FROM friend_to F INNER JOIN users U ON U.id = F.user_id_from WHERE F.user_id_to = ? AND F.activated = false"
                , new Object[] { userId },
                (resultSet, i) -> {
                    UserInvite userInvite = new UserInvite();
                    userInvite.setId(resultSet.getLong("id"));
                    userInvite.setInviteText(resultSet.getString("invite_text"));
                    userInvite.setUsernameFrom(resultSet.getString("username"));
                return userInvite;
        });
    }

    @Override
    public List<UserInvite> getFriendsList(Long userId) {
        return jdbcTemplate.query("SELECT id, username\n" +
                        "FROM users\n" +
                        "WHERE id IN (SELECT user_id_from\n" +
                        "FROM friend_to\n" +
                        "WHERE user_id_to = ? AND activated = true)\n" +
                        "OR id IN (SELECT user_id_to\n" +
                        "FROM friend_to\n" +
                        "WHERE user_id_from = ? AND activated = true)"
                , new Object[] { userId, userId },
                (resultSet, i) -> {
                    UserInvite userInvite = new UserInvite();
                    userInvite.setUserIdFrom(resultSet.getLong("id"));
                    userInvite.setUsernameFrom(resultSet.getString("username"));
                    return userInvite;
                });
    }

    @Override
    public void accept(Long id) {
        jdbcTemplate.update(
                "UPDATE friend_to SET activated = ? WHERE id = ?",
                true,
                id);
    }

    @Override
    public void decline(Long id) {
        jdbcTemplate.update(
                "DELETE FROM friend_to WHERE id = ?",
                id
        );
    }

    @Override
    public void deleteFriendFromList(Long userId, Long userIdDelete) {
        jdbcTemplate.update(
                "DELETE FROM friend_to WHERE user_id_from = ? AND user_id_to = ? " +
                        "OR user_id_to = ? AND user_id_from = ?",
                userId, userIdDelete, userId, userIdDelete
        );
    }
}
