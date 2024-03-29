package com.study.board.user.validator;

import com.study.board.user.domain.repository.UserRepository;
import com.study.board.user.presentation.dto.UserSaveRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckEmailValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public CheckEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserSaveRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserSaveRequestDTO userSaveRequestDTO = (UserSaveRequestDTO) target;
        if (UserRepository.existsByEmail(userSaveRequestDTO.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}
