package com.projectjava.rating.repository;

import com.projectjava.rating.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findRatingsByUserId(@Param("userId") Integer userId);
    List<Rating> findRatingsBySongId(@Param("songId") Integer songId);
    Rating findRatingByUserIdAndSongId(@Param("userId") Integer userId,@Param("songId") Integer songId);
}