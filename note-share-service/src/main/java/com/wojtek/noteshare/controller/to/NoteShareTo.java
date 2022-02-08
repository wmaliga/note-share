package com.wojtek.noteshare.controller.to;

import com.wojtek.noteshare.repository.model.NoteType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NoteShareTo {
    @NotNull
    private NoteType type;
    private String password;
    @NotBlank
    private String title;
    @NotBlank
    private String data;
}
