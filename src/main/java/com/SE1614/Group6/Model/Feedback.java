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
    private Integer feedback_id;

    @ManyToOne @JoinColumn(name ="product_id",nullable = false)
    private Product product;

    @Column(nullable = false)
    private String comment;

    @Column
    private Integer star;
    @ManyToOne @JoinColumn(name = "user_id")
    User user;



}