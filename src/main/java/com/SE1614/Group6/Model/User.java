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

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "status_id",nullable = false)
    private User_status user_status;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "state_id",nullable = false)
    private State state;

    @Column(nullable = false,length = 45)
    private String user_name;

    @Column(length = 45,nullable = false)
    private String password;


    @Column(nullable = false,unique = true,length = 45)
    private String email;

    @Column
    private String full_name;

    @Column
    private String gender;

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


    @Override
    public String toString() {
        return "User{" +
                "user_id=" + id +
                ", role=" + role +
                ", user_status=" + user_status +
                ", state=" + state +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", full_name='" + full_name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
