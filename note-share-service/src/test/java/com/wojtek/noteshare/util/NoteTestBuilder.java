package com.wojtek.noteshare.util;

import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.repository.model.NoteType;

import static java.time.LocalDate.now;

public class NoteTestBuilder {

    public static Note publicNote() {
        return note(NoteType.PUBLIC);
    }

    public static Note privateNote() {
        return note(NoteType.PRIVATE);
    }

    private static Note note(NoteType type) {
        return Note.builder()
                .type(type)
                .password(type == NoteType.PRIVATE ? "password" : null)
                .title("title")
                .expirationDate(now().plusDays(10))
                .data("data")
                .build();
    }
}
