package com.aaron.AA2_AD.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "loan_date")
    private LocalDateTime loanDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "fine_price")
    private float finePrice;

    @Column
    private Boolean onTime;

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
