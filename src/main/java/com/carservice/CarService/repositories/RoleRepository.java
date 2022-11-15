package com.carservice.CarService.repositories;

import com.carservice.CarService.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Nullable
    Role findByName(Role.RoleName name);
}
