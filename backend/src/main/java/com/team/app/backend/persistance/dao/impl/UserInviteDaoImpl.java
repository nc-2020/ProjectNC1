package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.UserInviteDao;
import com.team.app.backend.persistance.dao.mappers.UserInviteRowMapper;
import com.team.app.backend.persistance.model.UserInvite;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@PropertySource("classpath:sql_query.properties")
@Repository
public class UserInviteDaoImpl implements UserInviteDao {
    private final JdbcTemplate jdbcTemplate;
    @Value("${user.send}")
    private String sqlSend;
    @Value("${user.getUserInvite}")
    private String sqlGetUserInvite;
    @Value("${user.getFriendsList}")
    private String sqlGetFriendsList;
    @Value("${user.accept}")
    private String sqlAccept;
    @Value("${user.decline}")
    private String sqlDecline;
    @Value("${user.deleteFriendFromList}")
    private String sqlDeleteFriendFromList;

    private UserInviteRowMapper userInviteRowMapper = new UserInviteRowMapper();

    public UserInviteDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void send(UserInvite userInvite) {
        jdbcTemplate.update(
                sqlSend,
                userInvite.isActivated(),
                userInvite.getInviteText(),
                userInvite.getInviteDate(),
                userInvite.getUserIdFrom(),
                userInvite.getUserIdTo());
    }

    @Override
    public List<UserInvite> getUserInvite(Long userId) {
        return jdbcTemplate.query(sqlGetUserInvite
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
        return jdbcTemplate.query(sqlGetFriendsList
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
        jdbcTemplate.update(sqlAccept, id);
    }

    @Override
    public void decline(Long id) {
        jdbcTemplate.update(sqlDecline, id);
    }

    @Override
    public void deleteFriendFromList(Long userId, Long userIdDelete) {
        jdbcTemplate.update(sqlDeleteFriendFromList, userId, userIdDelete, userId, userIdDelete);
    }
}
