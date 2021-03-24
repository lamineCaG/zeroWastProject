package com.projet.react.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,name = "id")
    private Long id;
    @Column(name = "firstname",length = 50,nullable = false)
    private String firstname;
    @Column(name = "lastname",length = 50,nullable = false)
    private String lastname;
    @Column(name = "email",length = 50,nullable = true)
    private String email;
    @Column(name = "password",length = 100,nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "userRole",nullable = false)
    private UserRole userRole;
    @Column(name = "locked")
    private Boolean locked = false;
    @Column(name = "enable")
    private Boolean enable = true;

    @Column(name = "date_de_creation",nullable = true)
    private LocalDate date_de_creation;

    public Users( String firstname, String lastname, String email, String password, UserRole userRole, LocalDate date_de_creation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.date_de_creation = date_de_creation;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority
                = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
