package com.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String author;

    @Column
    private Double price;

    @Column
    private String language;

    @Column(name = "publish_year")
    private Integer publishYear;

    @Column
    private Integer quantity;

    @Column
    private Boolean visible;
}
