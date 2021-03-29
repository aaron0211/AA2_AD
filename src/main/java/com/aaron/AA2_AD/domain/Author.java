package com.aaron.AA2_AD.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "author")
public class Author {

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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateBirth;

    @Column
    private float height;

    @Column
    private Boolean isAlive;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
