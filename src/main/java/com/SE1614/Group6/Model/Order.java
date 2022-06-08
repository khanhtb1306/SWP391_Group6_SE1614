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
    private Integer id;

    @ManyToOne
    @JoinColumn(name="userId",nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="statusId",nullable=false)
    private Status status;

    @ManyToOne
    @JoinColumn(name="saleId",nullable=false)
    private User sale;

    @Column
    private String notes;

    @Column
    private Date orderDate;

    @Column
    private String userSession;


}
