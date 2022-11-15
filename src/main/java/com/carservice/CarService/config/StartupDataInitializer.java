package com.carservice.CarService.config;

import com.carservice.CarService.data.Privilege;
import com.carservice.CarService.data.Role;
import com.carservice.CarService.data.User;
import com.carservice.CarService.exceptions.AuthenticationException;
import com.carservice.CarService.service.api.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.carservice.CarService.data.Role.RoleName.*;

@Component
public class StartupDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private boolean isAlreadySetup;

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (isAlreadySetup) return;
        createDefaultUsersAndRoles();
    }

    private void createDefaultUsersAndRoles() throws AuthenticationException {
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        Role adminRole = createRoleIfNotFound(ADMIN, adminPrivileges);
        Role managerRole = createRoleIfNotFound(MANAGER, Collections.singletonList(readPrivilege));
        Role masterRole = createRoleIfNotFound(MASTER, Collections.singletonList(readPrivilege));
        createRoleIfNotFound(CUSTOMER, Collections.singletonList(readPrivilege));

        User admin = new User("admin",
                "admin",
                "adminFullname",
                null,
                null
        );
        User manager = new User("manager",
                "manager",
                "managerFullName",
                null,
                null
        );
        User master = new User("master",
                "master",
                "masterFullName",
                null,
                null
        );
        admin.setRoles(Collections.singletonList(adminRole));
        admin.setEnabled(true);
        if (!userService.ifUserExist("admin")) {
            userService.saveUser(admin);
        }
        manager.setRoles(Collections.singletonList(managerRole));
        manager.setEnabled(true);
        if (!userService.ifUserExist("manager")) {
            userService.saveUser(manager);
        }
        master.setRoles(Collections.singletonList(masterRole));
        master.setEnabled(true);
        if (!userService.ifUserExist("master")) {
            userService.saveUser(master);
        }

        isAlreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = userService.findPrivilegeByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            userService.savePrivilege(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(Role.RoleName name, List<Privilege> privileges) {
        Role role = userService.findRoleByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            userService.saveRole(role);
        }
        return role;
    }
}
