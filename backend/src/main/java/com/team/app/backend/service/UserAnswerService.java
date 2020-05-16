package com.team.app.backend.service;

import com.team.app.backend.dto.UserAnswerDto;
import com.team.app.backend.persistance.model.UserAnswer;

public interface UserAnswerService {
    UserAnswer addNewUserAnswer(UserAnswerDto userAnswerDto);
}
