package com.SE1614.Group6.Model;


import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="productid",nullable = false)
    private Product product;

    @Column(nullable = false)
    private char comment;

    @Column
    private Integer star;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;



}