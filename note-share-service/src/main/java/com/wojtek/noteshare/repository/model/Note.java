package com.wojtek.noteshare.repository.model;

import lombok.*;

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
    private String title;

    @NonNull
    private LocalDate expirationDate;

    @NotBlank
    private String data;
}
