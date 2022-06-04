package com.example.crud.Model;


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
    private Integer userid;
    private Integer productid;
    private char comment;
    private Integer star;
    @ManyToOne @JoinColumn(name = "productid")
    Product product;
    @ManyToOne @JoinColumn(name = "userid")
    User user;
    @Override
    public String toString() {
        return "FeedBack{" +
                "user id=" + userid +
                ", product name='" + productid + '\'' +
                ", comment='" + comment + '\'' +
                ", star='" + star + '\'' +
                '}';
    }


}
