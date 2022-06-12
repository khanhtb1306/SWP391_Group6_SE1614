package com.SE1614.Group6.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name="user_Id",nullable=false)
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private Order_status order_status;

    @ManyToOne
    @JoinColumn(name="sale_Id",nullable=false)
    private User sale;

    @Column
    private String notes;

    @Column
    private Date order_Date;

    @Column
    private String user_session;
    @OneToMany(mappedBy = "order")

    private Set<OrderDetail> order_etails;

}
