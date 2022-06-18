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
    @JoinColumn(name="order_id",nullable=false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id",nullable=false)
    private Product product;

    @Column
    private Integer quantity;

    @Column
    private Integer unit_price;
}
