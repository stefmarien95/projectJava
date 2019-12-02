package com.projectjava.rating.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.math.BigInteger;

@Data
@Document(collection = "Rating")
public class Rating {
    @Id
    private BigInteger id;
    private Integer rating;
    private Integer userId;
    private Integer songId;
}