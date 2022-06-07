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

    @ManyToOne
    @JoinColumn(name="roleId",nullable=false)
    private Role role;

    @ManyToOne
    @JoinColumn(name="statusId",nullable=false)
    private Status status;
    @Column(nullable = false,length = 45)
    private String username;
    @Column(length = 45,nullable = false)
    private String password;
    @Column
    private String gender;
    @Column(nullable = false,unique = true,length = 45)
    private String email;
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
    private Set<Order> saleUser;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
