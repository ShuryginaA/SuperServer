package com.carservice.CarService.data.dto;

import com.carservice.CarService.data.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

@Data
@NoArgsConstructor
public class AuthDto {
    private Role.RoleName role;
    private Long id;

    public AuthDto(Authentication authentication){

    }
}
