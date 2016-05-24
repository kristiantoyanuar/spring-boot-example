package com.kristiantoyanuar.model;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Hold all information related to user account of application
 *
 * Created by Kristianto Yanuar on 5/21/16.
 */
@Entity
@Table(name = "user_account")
public class User extends AbstractEntity<Long> implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    @Column(unique = true)
    @Size(min = 3)
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @Column
    private Boolean accountNonExpired;

    @Column
    private Boolean accountNonLocked;

    @Column
    private Boolean credentialsNonExpired;

    @Column
    private Boolean enabled;

    @ElementCollection(targetClass = ApplicationAuthority.class)
    @Enumerated(EnumType.STRING)
    private Collection<ApplicationAuthority> grantedAuthorities;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<ApplicationAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<ApplicationAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
