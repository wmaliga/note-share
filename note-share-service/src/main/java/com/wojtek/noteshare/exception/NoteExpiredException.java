package com.wojtek.noteshare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.GONE, reason = "Note expired")
public class NoteExpiredException extends RuntimeException {
}
