package com.projectjava.playlist.repository;

import com.projectjava.playlist.entity.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends MongoRepository<Playlist, String> {
    List<Playlist> findPlaylistByUserId(@Param("userId") Integer userId);
    List<Playlist> findPlaylistByName(@Param("name") String name);
    List<Playlist> findPlaylistByUserIdAndName(@Param("userId") Integer userId, @Param("name") String name);
}
