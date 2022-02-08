package com.wojtek.noteshare.controller.to;

import com.wojtek.noteshare.repository.model.NoteType;
import lombok.Data;

@Data
public class NoteTo {
    private Long id;
    private NoteType type;
    private String title;
    private String data;
}
