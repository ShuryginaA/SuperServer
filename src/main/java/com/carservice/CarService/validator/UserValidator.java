package com.carservice.CarService.validator;

import com.carservice.CarService.data.User;
import com.carservice.CarService.service.api.UserService;
import com.carservice.CarService.viewModel.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationForm userForm = (RegistrationForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (userForm.getUsername().length() < 6 || userForm.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findUserByUsername(userForm.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (userForm.getPassword().length() < 6 || userForm.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userForm.getConfirmingPassword().equals(userForm.getPassword())) {
            errors.rejectValue("confirmingPassword", "Diff.userForm.passwordConfirm");
        }
    }
}
