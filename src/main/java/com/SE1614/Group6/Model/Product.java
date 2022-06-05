package com.example.crud.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productid;
    private char productname;
    private int quantity;
    private char categoryid;
    private int originalprice;
    private char detail;
    private char title;
    private int saleprice;
    private char images;
    private Date updatedate;

    @OneToMany(mappedBy="feedback")
    List<Feedback> feedback;
    @OneToMany(mappedBy = "category")
    List<Category> category;
    @OneToMany(mappedBy= "orderdetail")
    List<OrderDetail> orderdetail;
}