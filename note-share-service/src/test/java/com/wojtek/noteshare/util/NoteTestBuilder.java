package com.wojtek.noteshare.util;

import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.repository.model.NoteType;

import static java.time.LocalDate.now;

public class NoteTestBuilder {

    public static Note publicNote() {
        return publicNoteBuilder().build();
    }

    public static Note.NoteBuilder publicNoteBuilder() {
        return note().type(NoteType.PUBLIC);
    }

    public static Note privateNote() {
        return privateNoteBuilder().build();
    }

    public static Note.NoteBuilder privateNoteBuilder() {
        return note().type(NoteType.PRIVATE).password("password");
    }

    private static Note.NoteBuilder note() {
        return Note.builder()
                .title("title")
                .expirationDate(now().plusDays(10))
                .data("data");
    }
}
