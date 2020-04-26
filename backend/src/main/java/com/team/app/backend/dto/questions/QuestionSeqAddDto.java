package com.team.app.backend.dto.questions;

import com.team.app.backend.dto.options.DefOptionDto;
import com.team.app.backend.dto.options.SeqOptionDto;
import com.team.app.backend.persistance.model.SeqOption;

import java.util.List;

public class QuestionSeqAddDto extends QuestionDto {

    private List<SeqOptionDto> options;

    public List<SeqOptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<SeqOptionDto> options) {
        this.options = options;
    }
}
