package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OptionObj {

    private Long id;
    private Long quest_id;

    public OptionObj(Long id, Long quest_id) {
        this.id = id;
        this.quest_id = quest_id;
    }
}
