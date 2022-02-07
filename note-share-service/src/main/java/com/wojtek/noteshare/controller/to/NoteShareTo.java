package com.wojtek.noteshare.controller.to;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NoteShareTo {
    @NotBlank
    private String title;
    @NotBlank
    private String data;
}
