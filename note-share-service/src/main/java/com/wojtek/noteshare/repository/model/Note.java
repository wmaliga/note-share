package com.wojtek.noteshare.repository.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String data;
}
