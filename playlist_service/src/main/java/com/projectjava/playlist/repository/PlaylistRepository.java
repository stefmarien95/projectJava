package com.projectjava.playlist.repository;

import com.projectjava.playlist.entity.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends MongoRepository<Playlist, String> {
    List<Playlist> findPlaylistsByUserId(@Param("userId") Integer userId);
    List<Playlist> findPlaylistsBySongId(@Param("songId") Integer songId);
    List<Playlist> findPlaylistsByName(@Param("name") String name);
    Playlist findPlaylistByUserIdAndName(@Param("userId") Integer userId, @Param("name") String name);
}