package com.projectjava.rating.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Rating")
public class Rating {
    @Id
    private String id;
    private Integer rating;
    private Integer userId;
    private Integer songId;
}