package ru.spring.boot_security.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.spring.boot_security.model.User;
import ru.spring.boot_security.service.UserService;

@Component
public class UserNameValidator implements Validator {

    private final UserService userService;

    public UserNameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            userService.UserNameCheck((User) target);
        } catch (IllegalArgumentException ignored) {
            errors.rejectValue("name", "",ignored.getMessage());
        }
    }
}
