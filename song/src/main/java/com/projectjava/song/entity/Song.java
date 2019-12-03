package com.projectjava.song.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@Document(collection = "Song")
public class Song {
    @Id
    private String id;
    private String title;
    private String artist;
    private String genre;
    private String cover;
    private String album;
    private String duration;
    private String userId ;

}
