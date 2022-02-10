package com.wojtek.noteshare.controller.to;

import com.wojtek.noteshare.repository.model.NoteType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NoteTo {
    private Long id;
    private NoteType type;
    private String title;
    private LocalDate expirationDate;
    private String data;
}
