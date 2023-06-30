package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    Integer price;
    @Temporal(TemporalType.DATE)
    @Column(name="Createdate")
    Date createDate = new Date();
    @ManyToOne
    @JoinColumn(name="Categoryid")
    Category category;
    String image;
}
