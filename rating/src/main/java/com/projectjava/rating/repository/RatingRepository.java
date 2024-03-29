package com.projectjava.rating.repository;

import com.projectjava.rating.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findRatingsByUserId(@Param("userId") int userId);
    List<Rating> findRatingsBySongId(@Param("songId") String songId);
    Rating findRatingByUserIdAndSongId(@Param("userId") int userId,@Param("songId") String songId);
}