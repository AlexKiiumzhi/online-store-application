package com.Alex.OnlineStoreApplications.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="product")
public class Product implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String name;

    @Column(nullable = false)
    private String brandName;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @Column(nullable = false)
    private BigDecimal price;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
