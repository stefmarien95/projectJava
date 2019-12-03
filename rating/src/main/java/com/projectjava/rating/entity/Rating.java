package com.projectjava.rating.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.math.BigInteger;

@Data
@Document(collection = "Rating")
public class Rating {
    @Id
    private String id;
    private Integer rating;
    private int userId;
    private String songId;
}