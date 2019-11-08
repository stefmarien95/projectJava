package com.projectjava.playlist.repository;

import com.projectjava.playlist.entity.playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends MongoRepository<playlist, String> {
    List<playlist> findPlaylistByUserId(@Param("userid") Integer userid);
    List<playlist> findPlaylistByName(@Param("name") String name);
    List<playlist> findPlaylistByUserIdAndName(@Param("userid") Integer userid,@Param("name") String name);
}
