package com.SE1614.Group6.Model;

import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Getter
@Setter
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "status_id", nullable = false)
    private User_status user_status;


    @Column(nullable = false, length = 45)
    private String first_name;

    @Column(nullable = false, length = 45)
    private String last_name;

    @Column(length = 255, nullable = false)
    private String password;


    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column
    private Boolean locked = false;
    @Column
    private Boolean enabled = false;

    @Enumerated(EnumType.STRING)
    private Gender Gender;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String avatar;

    @OneToMany(mappedBy = "user")
    private Set<Blog> blog;

    @OneToMany(mappedBy = "user")
    private Set<Order> order;

    @OneToMany(mappedBy = "sale")
    private Set<Order> sale_User;

    @Transient
    public String getPhotosImagePath() {
        if (avatar == null || id == null) return null;

        return "/user_avatar/" + id + "/" + avatar;
    }

    public User(String first_name, String last_name, String password, String email, Role role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public String getAvatar() {
        return avatar;
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
        return enabled;
    }
}
