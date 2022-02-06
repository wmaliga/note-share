package com.wojtek.noteshare.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAllNotes() throws Exception {
        this.mockMvc.perform(get("/api/v1/notes"))
                .andExpect(status().isOk());
    }

    @Test
    public void findNoteById() throws Exception {
        this.mockMvc.perform(get("/api/v1/notes/1"))
                .andExpect(status().isOk());
    }
}
