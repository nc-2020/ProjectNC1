package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.DefaultQuest;
import com.team.app.backend.persistance.model.Option;
import com.team.app.backend.persistance.model.SeqOption;

public interface OptionDao {

    void addOption(Option option);

    void addSeqOption(SeqOption seqOption);

    void addDefaultOption(DefaultQuest defaultQuest);
}
