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

    @ManyToOne @JoinColumn(name ="product_id")
    private Product product;

    @Column(nullable = false)
    private String comment;

    @Column
    private Integer star;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;



}