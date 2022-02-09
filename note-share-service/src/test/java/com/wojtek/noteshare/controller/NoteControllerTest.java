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

import static java.lang.String.format;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findPublicNotesTest() throws Exception {
        String publicNoteId = shareTestNote(NoteTestBuilder.publicNote());
        String privateNoteId = shareTestNote(NoteTestBuilder.privateNote());

        this.mockMvc.perform(get("/api/v1/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(format("$.[?(@.id==%s)]", publicNoteId)).exists())
                .andExpect(jsonPath(format("$.[?(@.id==%s)]", privateNoteId)).doesNotExist());
    }

    @Test
    public void getPublicNoteTest() throws Exception {
        String publicNoteId = shareTestNote(NoteTestBuilder.publicNote());

        this.mockMvc.perform(get("/api/v1/notes/" + publicNoteId))
                .andExpect(status().isOk());
    }

    @Test
    public void getNoteMissingTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/notes/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getNoteTypeTest() throws Exception {
        String publicNoteId = shareTestNote(NoteTestBuilder.publicNote());

        this.mockMvc.perform(get(format("/api/v1/notes/%s/type", publicNoteId)))
                .andExpect(status().isOk())
                .andExpect(content().string("\"PUBLIC\""));
    }

    @Test
    public void sharePublicNoteTest() throws Exception {
        Note note = NoteTestBuilder.publicNote();

        this.mockMvc.perform(post("/api/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(note)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    public void sharePrivateNoteTest() throws Exception {
        Note note = NoteTestBuilder.privateNote();

        this.mockMvc.perform(post("/api/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(note)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
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

    private String shareTestNote(Note note) throws Exception {
        return this.mockMvc.perform(post("/api/v1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(note)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");
    }
}
