package com.study.board.user.presentation;


import com.study.board.user.presentation.dto.UserSaveRequestDTO;
import com.study.board.user.service.UserService;
import com.study.board.user.validator.CheckEmailValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final CheckEmailValidator checkEmailValidator;

    // 유효성 검사
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators((Validator) checkEmailValidator);
    }



    @PostMapping("/users/new")
    public String createUser(@Valid UserSaveRequestDTO userSaveRequestDTO, Errors errors, Model model) {
        // 검증
        if (errors.hasErrors()) {
            // 회원가입 실패 시 입력 데이터 유지
            model.addAttribute("dto", userSaveRequestDTO);

            // 유효성 검사를 통과하지 못한 필드와 메세지 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            // 회원가입 페이지로 리턴
            return "/users/createUserForm";
        }

        Long userId = userService.join(userSaveRequestDTO);

        return "redirect:/board/list";
    }

    /**
    @GetMapping("/")
    public String Home() {
        return "redirect:/board/list";
    }

    @GetMapping("/users/new")
    public String createUserForm() {
        return "createUserForm";
    }

    @PostMapping("/users/new")
    public String createUser(UserSaveRequestDTO userSaveRequestDTO) {
        Long userId = userService.join(userSaveRequestDTO);
        return "redirect:/board/list";
    }**/
}