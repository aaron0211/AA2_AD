package com.aaron.AA2_AD.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Identificador del autor", example = "1",required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del autor", example = "Miguel Cervantes",required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Fecha de nacimiento", example = "24/02/1965")
    @Column(name = "date_birth")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateBirth;

    @Schema(description = "Altura", example = "1.75", defaultValue = "0.00")
    @Column
    private float height;

    @Schema(description = "Está vivo?",example = "true")
    @Column
    private Boolean isAlive;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Book> books;
}
