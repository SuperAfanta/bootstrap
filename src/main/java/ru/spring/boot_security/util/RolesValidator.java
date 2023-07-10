package ru.spring.boot_security.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RolesValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return String[].class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target == null) {
            errors.rejectValue("roleSet", "", "Выберите роли");
        }
    }
}
