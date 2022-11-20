package com.carservice.CarService.service.impl;

import com.carservice.CarService.data.Role;
import com.carservice.CarService.data.User;
import com.carservice.CarService.exceptions.AuthenticationException;
import com.carservice.CarService.repositories.RoleRepository;
import com.carservice.CarService.repositories.UserRepository;
import com.carservice.CarService.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(User user) throws AuthenticationException {
        if (user.getRoles() == null)
            throw new AuthenticationException("User " + user.getUsername() + " must have roles");

        User anotherUser = userRepository.findByUsername(user.getUsername());
        log.info("processing user: " + user.getUsername());
        if (anotherUser == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new AuthenticationException("User name " + user.getUsername() + " is already reserved");
        }
    }

    @Override
    public void updateUser(User user) {
        User oldUser = userRepository.findByUsername(user.getUsername());
        if (oldUser == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            user.setId(oldUser.getId());
        }
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean ifUserExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public boolean removeUserByUsername(String username) {
        return userRepository.deleteByUsername(username) != null;
    }

    @Override
    public boolean removeUser(User user) {
        boolean exists = userRepository.findByUsername(user.getUsername()) != null;
        if (exists) userRepository.delete(user);
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User doesn't exist");

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthorities());
    }

    @Override
    public Role findRoleByName(Role.RoleName name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role.RoleName> findUserRoleByUsername(String username) {
        User user=userRepository.findByUsername(username);
        if(user!=null){
            return user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        }
        throw new UsernameNotFoundException("No such user");
    }
}
