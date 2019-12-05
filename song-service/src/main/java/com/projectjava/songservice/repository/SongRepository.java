package com.projectjava.songservice.repository;

import com.projectjava.songservice.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findSongsByTitleContaining(@Param("title") String title);
    Song findSongById(@Param("songId") int songId);
    List<Song> findSongByUserId(@Param("userId") int userId);
}
