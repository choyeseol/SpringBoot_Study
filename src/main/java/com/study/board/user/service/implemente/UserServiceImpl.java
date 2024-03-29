package com.study.board.user.service.implemente;

import com.study.board.user.domain.entity.User;
import com.study.board.user.domain.repository.UserRepository;
import com.study.board.user.presentation.dto.UserSaveRequestDTO;
import com.study.board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long join(UserSaveRequestDTO userSaveRequestDTO) {
        userSaveRequestDTO.setPassword(passwordEncoder.encode(userSaveRequestDTO.getPassword()));

        User user = User.builder()
                .email(userSaveRequestDTO.getEmail())
                .username(userSaveRequestDTO.getUsername())
                .password(userSaveRequestDTO.getPassword())
                .build();

        return userRepository.save(user).getId();
    }

    @Override
    public Map<String, String> validateHandling(Errors errors) {
        return null;
    }
}