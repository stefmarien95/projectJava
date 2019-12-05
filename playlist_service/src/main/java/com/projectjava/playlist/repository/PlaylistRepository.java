package com.projectjava.playlist.repository;

import com.projectjava.playlist.entity.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends MongoRepository<Playlist, String> {
    List<Playlist> findPlaylistsByUserId(@Param("userId") int userId);
    List<Playlist> findPlaylistsBySongId(@Param("songId") String songId);
    Playlist findPlaylistById(@Param("playlistId") String playlistId);
}