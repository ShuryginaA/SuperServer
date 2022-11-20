package com.carservice.CarService.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Entity(name = "roles")

public class Role {

    public enum RoleName{
    ADMIN ("ADMIN"),MANAGER("MANAGER"),MASTER("MASTER"),CUSTOMER("CUSTOMER");
    private String roleName;
        RoleName(String name) {
            this.roleName=name;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private final RoleName name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

//    @ManyToMany
//    @JoinTable(name = "roles_privileges",
//            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
//    private List<Privilege> privileges;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
