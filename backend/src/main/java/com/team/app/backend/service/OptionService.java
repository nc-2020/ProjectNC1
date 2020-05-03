package com.team.app.backend.service;

import com.team.app.backend.persistance.model.DefaultQuest;
import com.team.app.backend.persistance.model.Option;
import com.team.app.backend.persistance.model.SeqOption;

import java.util.List;

public interface OptionService {

    List<DefaultQuest> getDefaultOpion(Long quest_id);

    List<Option> getOpions(Long quest_id);

    List<SeqOption> getSeqOpion(Long quest_id);
}
