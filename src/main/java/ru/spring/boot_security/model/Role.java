package ru.spring.boot_security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;

    @Transient
    @ManyToMany
    private Set<User> users;

    @Transient
    private final static Map<String, Role> roleMap= new HashMap<>();

    protected Role() {

    }

    protected Role(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static void setRole(String name, String value) {
        roleMap.put(name, new Role(name, value));
    }

    public static Role getRole(String name) {
        return roleMap.get(name);
    }


    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
