package com.aaron.AA2_AD.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "loan")
public class Loan {

    @Schema(description = "Identificador del prestamo", example = "1",required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Fecha del prestamo", example = "01/04/2021 18:30:00")
    @Column(name = "loan_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime loanDate;

    @Schema(description = "Fecha de devolucion", example = "05/04/2021 10:00:00")
    @Column(name = "return_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime returnDate;

    @Schema(description = "Precio de la multa", example = "2.50", defaultValue = "0.00")
    @Column(name = "fine_price")
    private float finePrice;

    @Schema(description = "Devuelto a tiempo?", example = "true")
    @Column
    private Boolean onTime;

    @Schema(description = "Observaciones del prestamo", example = "Se presta con un tiempo mayor al habitual")
    @Column
    private String observations;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference(value = "member")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference(value = "book")
    private Book book;
}
