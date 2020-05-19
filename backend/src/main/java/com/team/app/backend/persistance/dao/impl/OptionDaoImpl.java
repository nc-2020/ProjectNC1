package com.team.app.backend.persistance.dao.impl;


import com.team.app.backend.persistance.dao.OptionDao;
import com.team.app.backend.persistance.dao.mappers.DefOptionRowMapper;
import com.team.app.backend.persistance.dao.mappers.OptionRowMapper;
import com.team.app.backend.persistance.dao.mappers.SeqOptionRowMapper;
import com.team.app.backend.persistance.model.DefaultQuest;
import com.team.app.backend.persistance.model.Option;
import com.team.app.backend.persistance.model.SeqOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class OptionDaoImpl implements OptionDao {

    private JdbcTemplate jdbcTemplate;
    public OptionDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private OptionRowMapper optionRowMapper;

    @Autowired
    private SeqOptionRowMapper seqoptionRowMapper;

    @Autowired
    private DefOptionRowMapper defoptionRowMapper;

    @Autowired
    Environment env;

    @Override
    public List<Option> getOptionQuest(Long id) {
        return jdbcTemplate.query(env.getProperty("get.option")
                ,new Object[] { id },
                optionRowMapper);
    }

    @Override
    public List<SeqOption> getSeqOptionQuest(Long id) {
        return jdbcTemplate.query( env.getProperty("get.seqoption")
                ,new Object[] { id },
                seqoptionRowMapper);
    }

    @Override
    public List<DefaultQuest> getDefaultQuest(Long id) {
        return jdbcTemplate.query( env.getProperty("get.defoption")
                ,new Object[] { id },
                defoptionRowMapper);
    }

    @Override
    public void addOption(Option option) {
        jdbcTemplate.update(
                env.getProperty("create.option"),
                option.getIs_correct(),
                option.getText(),
                option.getImage(),
                option.getQuest_id()
                );
    }

    @Override
    public void addSeqOption(SeqOption seqOption) {
        jdbcTemplate.update(
                env.getProperty("create.seqoption"),
                seqOption.getSerial_num(),
                seqOption.getText(),
                seqOption.getImage(),
                seqOption.getQuest_id()
                );
    }

    @Override
    public void addDefaultOption(DefaultQuest defaultQuest) {
       jdbcTemplate.update(
               env.getProperty("create.defoption"),
                defaultQuest.getAnswer(),
                defaultQuest.getImage(),
                defaultQuest.getQuest_id()
       );
    }
}
