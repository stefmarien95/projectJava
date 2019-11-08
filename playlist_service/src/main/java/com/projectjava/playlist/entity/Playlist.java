package com.projectjava.playlist.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="Playlist")
public class Playlist {
    @Id
    private String id;
    private String name;
    private Integer userId;
    private Integer songId;
}
