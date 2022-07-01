package com.SE1614.Group6.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
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
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="category_id",nullable=false)
    private Category category;

    @Column
    private Integer original_price;

    @Column
    private String product_detail;

    @Column
    private String detail;

    @Column
    private String title;

    @Column
    private Integer sale_price;

    @Column
    private String images;

    @Column(nullable = false)
    private Date update_date;

}