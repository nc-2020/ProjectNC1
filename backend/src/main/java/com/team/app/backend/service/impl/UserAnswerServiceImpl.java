package com.team.app.backend.service.impl;

import com.team.app.backend.dto.UserAnswerDto;
import com.team.app.backend.persistance.model.UserAnswer;
import com.team.app.backend.service.UserAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAnswerServiceImpl implements UserAnswerService {
    @Override
    public UserAnswer addNewUserAnswer(UserAnswerDto userAnswerDto) {
        return null;
    }
}
