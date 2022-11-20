package com.carservice.CarService.viewModel;

import com.carservice.CarService.data.User;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationForm {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String fullName;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private String email;

    public User toUser() {
        User user = new User(username, fullName, phoneNumber, email);
        user.setPassword(password);
        return user;
    }
}
