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
    @JoinColumn(name="user_id",nullable=true)
    private User user;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name="blog_status_id",nullable=true)
    private Blog_status blog_status;

    @ManyToOne
    @JoinColumn(name="category_id",nullable=true)
    private Category category;

    @Column
    private String title;

    @Column
    private String views;

    @Column
    private String updateDate;

    @Column
    private String image_Link;

    @Column
    private String content;

}
