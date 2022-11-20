package com.carservice.CarService.data.dto;

import com.carservice.CarService.data.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    private String roleName;
    private Long id;
}
