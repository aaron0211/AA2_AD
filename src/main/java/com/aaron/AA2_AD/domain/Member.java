package com.aaron.AA2_AD.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column
    private String surname;

    @Column(name = "date_birth")
    private LocalDateTime dateBirth;

    @Column
    private float height;

    @Column
    private Boolean activ;

    @OneToMany(mappedBy = "member")
    private List<Loan> loans;
}