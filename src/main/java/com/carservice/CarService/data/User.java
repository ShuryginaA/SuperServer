package com.carservice.CarService.data;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name="users")
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
    private boolean enabled = true;
    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Order> orders;

    public User(String username, String password,String fullName, String phoneNumber, String email) {
        this.username = username;
        this.fullName = fullName;
        this.password=password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String username, String fullName, String phoneNumber, String email) {
        this.username = username;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPrivilegesAndRolesAsStrings(roles));
    }

    @Transactional
    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    private List<String> getPrivilegesAndRolesAsStrings(List<Role> roles) {
        List<String> authorities = new ArrayList<>(roles.size());
        for (Role role: roles) {
            authorities.add(role.getName().toString());
        }
        return authorities;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !tokenExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
