package com.wojtek.noteshare.service.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteService {

    @GetMapping("")
    public String notes() {
        return "TODO";
    }
}
