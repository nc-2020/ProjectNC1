package com.team.app.backend.rest;


import com.team.app.backend.persistance.model.DefaultQuest;
import com.team.app.backend.persistance.model.Option;
import com.team.app.backend.persistance.model.SeqOption;
import com.team.app.backend.service.OptionService;
import com.team.app.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class OptionsController {

    @Autowired
    OptionService optionService;

    @GetMapping("/options/{id}")
    public List<Option> getOptions(@PathVariable("id") long id) {
        return optionService.getOpions(id);
    }
    @GetMapping("/default_options/{id}")
    public List<DefaultQuest> getDefOptions(@PathVariable("id") long id) {
        return optionService.getDefaultOpion(id);
    }
    @GetMapping("/sequence_options/{id}")
    public List<SeqOption> getSeqOptions(@PathVariable("id") long id) {
        return optionService.getSeqOpion(id);
    }

}
