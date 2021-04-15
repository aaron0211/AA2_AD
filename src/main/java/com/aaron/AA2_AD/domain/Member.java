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
@Entity(name = "member")
public class Member {

    @Schema(description = "Identificador del socio", example = "1",required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del socio", example = "Manuel")
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Direccion del socio", example = "Calle Mayor 1")
    @NotBlank
    @Column
    private String address;

    @Schema(description = "Fecha de nacimiento", example = "02/02/1999 12:00:00")
    @Column(name = "date_birth")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateBirth;

    @Schema(description = "Altura", example = "1.75")
    @Column
    private float height;

    @Schema(description = "Activo el ultimo año", example = "false")
    @Column
    private Boolean activ;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Loan> loans;
}