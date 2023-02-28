package com.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale")
public class SaleEntity {

    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "book_id")
    private Integer bookId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private BookEntity book;

    @Column
    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "created_date")
    private LocalDateTime createdDate;


    public SaleEntity(ProfileEntity profile, BookEntity book, Integer quantity) {
        this.profile = profile;
        this.book = book;
        this.quantity = quantity;
        totalPrice = book.getPrice() * quantity;
        createdDate = LocalDateTime.now();
    }
}
