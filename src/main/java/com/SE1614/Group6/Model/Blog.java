package com.SE1614.Group6.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="authorID",nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="statusID",nullable=false)
    private Status status;

    @ManyToOne
    @JoinColumn(name="categoryID",nullable=false)
    private Category category;

    @Column
    private String title;

    @Column
    private String views;

    @Column
    private Date updateDate;

}
