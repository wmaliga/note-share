package com.wojtek.noteshare.repository.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NoteType type;

    private String password;

    @NotBlank
    @Length(max = 100)
    private String title;

    private LocalDate expirationDate;

    @NotBlank
    @Length(max = 4000)
    private String data;
}
