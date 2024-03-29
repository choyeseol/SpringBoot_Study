package com.study.board.user.service;

import com.study.board.user.presentation.dto.UserSaveRequestDTO;
import com.study.board.user.presentation.dto.UserSaveRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Map;

@Service
public interface UserService {
    Long join(UserSaveRequestDTO userSaveRequestDTO);

    Map<String, String> validateHandling(Errors errors);
}