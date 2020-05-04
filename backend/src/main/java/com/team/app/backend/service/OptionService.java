package com.team.app.backend.service;

import com.team.app.backend.persistance.model.DefaultQuest;
import com.team.app.backend.persistance.model.Option;
import com.team.app.backend.persistance.model.SeqOption;

import java.util.List;

public interface OptionService {

    List<DefaultQuest> getDefaultOption(Long quest_id);

    List<Option> getOptions(Long quest_id);

    List<SeqOption> getSeqOption(Long quest_id);
}
