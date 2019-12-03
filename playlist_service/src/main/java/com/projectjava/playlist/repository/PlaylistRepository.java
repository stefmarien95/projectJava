package com.projectjava.playlist.repository;

import com.projectjava.playlist.entity.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends MongoRepository<Playlist, String> {
    List<Playlist> findPlaylistsByUserId(@Param("userId") String userId);
    List<Playlist> findPlaylistsBySongId(@Param("songId") String songId);
    List<Playlist> findPlaylistsByName(@Param("name") String name);
    Playlist findPlaylistByUserIdAndName(@Param("userId") String userId, @Param("name") String name);
}