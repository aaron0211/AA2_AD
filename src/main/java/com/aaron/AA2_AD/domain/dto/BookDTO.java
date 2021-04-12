package com.aaron.AA2_AD.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookDTO {
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime datePurchase;
    private float prize;
    private String isbn;
    private String editorial;
    private String nameAuthor;
}
