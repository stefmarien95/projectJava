package com.projectjava.song.repository;

import com.projectjava.song.entity.Song;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
public interface SongRepository extends MongoRepository<Song, Integer> {
    Song findSongByTitle(@Param("title") String title);
}

