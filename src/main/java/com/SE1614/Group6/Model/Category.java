package com.SE1614.Group6.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false,unique = true)
    private String value;

    /*@OneToMany(mappedBy = "category")
    private Set<Blog> blog;*/
}
