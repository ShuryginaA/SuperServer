package com.carservice.CarService.service.api;

import com.carservice.CarService.data.Privilege;
import com.carservice.CarService.data.Role;
import com.carservice.CarService.data.User;
import com.carservice.CarService.exceptions.AuthenticationException;

import java.util.List;

public interface UserService {

    void saveUser(User user) throws AuthenticationException;

    void updateUser(User user);

    User findUserByUsername(String username);

    boolean ifUserExist(String username);

    boolean removeUserByUsername(String username);

    boolean removeUser(User user);

    Role findRoleByName(Role.RoleName name);

    void saveRole(Role role);

    Privilege findPrivilegeByName(String name);

    void savePrivilege(Privilege privilege);

    List<Role.RoleName> findUserRoleByUsername(String username);
}
