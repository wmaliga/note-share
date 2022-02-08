package com.wojtek.noteshare.util;

import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.repository.model.NoteType;

public class NoteTestBuilder {

    public static Note publicNote() {
        return Note.builder().type(NoteType.PUBLIC).title("title").data("data").build();
    }

    public static Note privateNote() {
        return Note.builder().type(NoteType.PRIVATE).password("password").title("title").data("data").build();
    }
}
