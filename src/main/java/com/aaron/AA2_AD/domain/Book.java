package com.aaron.AA2_AD.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "book")
public class Book {

    @Schema(description = "Identificador del libro", example = "1",required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Título del libro", example = "El quijote",required = true)
    @NotBlank
    @Column
    private String title;

    @Schema(description = "Fecha de compra", example = "01/04/2021 16:00:00")
    @Column(name = "date_purchase")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime datePurchase;

    @Schema(description = "Precio de compra del libro", example = "16.99", defaultValue = "0.00")
    @Column(name = "precio_compra")
    @Min(value = 0)
    private float prize;

    @Schema(description = "Estado del libro", example = "true")
    @Column
    private Boolean isGood;

    @Schema(description = "Identificador del libro", example = "9788493806125")
    @Column
    private String isbn;

    @Schema(description = "Editorial", example = "Pluton Ediciones")
    @Column
    private String editorial;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference(value = "author")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans;
}
