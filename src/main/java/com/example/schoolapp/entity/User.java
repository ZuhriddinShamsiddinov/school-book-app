package com.example.schoolapp.entity;


import com.example.schoolapp.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;


@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> bookList;
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User(String username, String password, RoleEnum roleEnum) {
        this.username = username;
        this.password = password;
        this.roleEnum = roleEnum;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleEnum.getPermissions()
                .stream().map(s -> (GrantedAuthority) new SimpleGrantedAuthority(s)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
