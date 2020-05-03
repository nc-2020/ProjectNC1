package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.OptionDao;
import com.team.app.backend.persistance.model.DefaultQuest;
import com.team.app.backend.persistance.model.Option;
import com.team.app.backend.persistance.model.SeqOption;
import com.team.app.backend.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionDao optionDao;


    @Override
    public List<DefaultQuest> getDefaultOpion(Long quest_id) {
        return optionDao.getDefaultQuest(quest_id);
    }

    @Override
    public List<Option> getOpions(Long quest_id) {
        return optionDao.getOptionQuest(quest_id);
    }

    @Override
    public List<SeqOption> getSeqOpion(Long quest_id) {
        return optionDao.getSeqOptionQuest(quest_id);
    }
}
