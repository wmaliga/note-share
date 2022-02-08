package com.wojtek.noteshare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojtek.noteshare.repository.model.Note;
import com.wojtek.noteshare.util.NoteTestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findPublicNotesTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/notes"))
                .andExpect(status().isOk());
    }

    @Test
    public void findNoteByIdTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/notes/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findNoteByIdMissingNoteTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/notes/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void sharePublicNoteTest() throws Exception {
        Note note = NoteTestBuilder.publicNote();

        this.mockMvc.perform(post("/api/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(note)))
                .andExpect(status().isCreated());
    }

    @Test
    public void sharePrivateNoteTest() throws Exception {
        Note note = NoteTestBuilder.privateNote();

        this.mockMvc.perform(post("/api/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(note)))
                .andExpect(status().isCreated());
    }

    @Test
    public void sharePrivateNoteWithoutPasswordTest() throws Exception {
        Note note = NoteTestBuilder.privateNote();
        note.setPassword("");

        this.mockMvc.perform(post("/api/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(note)))
                .andExpect(status().isUnprocessableEntity());
    }
}
