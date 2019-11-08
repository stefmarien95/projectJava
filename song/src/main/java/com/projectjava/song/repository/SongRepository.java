package com.projectjava.song.repository;

import com.projectjava.song.entity.Song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
public interface SongRepository extends JpaRepository<Song, Integer> {
    Song findSongByTitle(@Param("title") String title);
}

