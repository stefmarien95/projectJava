package com.projectjava.playlist.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.util.List;

@Data
@Document(collection = "Playlist")
public class Playlist {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getSongId() {
        return songId;
    }

    public void setSongId(List<String> songId) {
        this.songId = songId;
    }

    @Id
    private String id;
    private String name;
    private String userId;
    private List<String> songId;
}