package com.SE1614.Group6.Model;

import javax.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 45)
    private String username;
    @Column(length = 45,nullable = false)
    private String password;
    @Column(nullable = false,length = 45,name="full_name")
    private String fullName;
    @Column(nullable = false)
    private Integer role;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false,unique = true,length = 45)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String avatar;

    @OneToMany(mappedBy = "user")
    private Set<Blog> blog;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
