package com.projectjava.playlist.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="playlist")
public class playlist {
    @Id
    private String id;
}
