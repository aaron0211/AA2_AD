package com.aaron.AA2_AD.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column
    private String title;

    @Column(name = "date_purchase")
    private LocalDateTime datePurchase;

    @Column(name = "precio_compra")
    private float price;

    @Column
    private Boolean isGood;

    @Column
    private String isbn;

    @Column
    private String editorial;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference(value = "author")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans;
}
