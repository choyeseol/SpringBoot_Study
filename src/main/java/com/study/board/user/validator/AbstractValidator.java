package com.study.board.user.validator;

import jakarta.validation.Validator;
import jakarta.validation.ValidationException; // 추가해야 하는 부분
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;

@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    public boolean supports(Class<?> clazz) {
        return true;
    }

//    @SuppressWarnings("unchecked")
//    public void validate(Object target, Errors errors) {
//        try {
//            doValidate((T) target, errors);
//        } catch(ValidationException e) { // 변경된 부분: RuntimeException -> ValidationException
//            log.error("중복 검증 에러", e);
//            throw e;
//        }
//    }

    protected abstract void doValidate(final T dto, final Errors errors);
}
