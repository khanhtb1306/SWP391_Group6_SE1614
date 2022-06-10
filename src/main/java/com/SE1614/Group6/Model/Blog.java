package com.SE1614.Group6.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="authorID",nullable=false)
    private User user;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",nullable=false)
    private Category category;

    @Column
    private String title;

    @Column
    private String views;

    @Column
    private String updateDate;

    @Column
    private String imageLink;

    @Column
    private String content;

}
