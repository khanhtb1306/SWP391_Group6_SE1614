package com.SE1614.Group6.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="orderdetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="orderid",nullable=false)
    private Order order;
    @ManyToOne
    @JoinColumn(name="productid",nullable=false)
    private Product product;
    @Column
    private Integer quantity;
    @Column
    private Integer unitprice;
}
