package com.SE1614.Group6.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productid;
    @Column
    private char productname;
    @Column
    private Integer quantity;


    @ManyToOne//câu lệnh truy vấn quan hệ bảng
    @JoinColumn(name="categoryid",nullable=false)
    private Category category;
    @Column
    private Integer originalprice;
    @Column
    private char detail;
    @Column
    private char title;
    @Column
    private Integer saleprice;
    @Column
    private char images;
    @Column(nullable = false)
    private Date updatedate;




}