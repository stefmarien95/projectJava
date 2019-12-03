package com.projectjava.playlist.edgeservice.models;

import lombok.Data;

@Data
public class ListingItem {
	private String titel;
	private String userId;
	private String songId;
	private int rating;

	public ListingItem() {}
	public ListingItem(String titel, int rating, String songId) {
		this.titel = titel;
		this.rating = rating;
		this.songId = songId;
	}
	public ListingItem(String titel, int rating, String userId, String songId) {
		this.titel = titel;
		this.rating = rating;
		this.userId = userId;
		this.songId = songId;
	}

	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
	    this.userId = userId;
    }
	public String getSongId() {
		return songId;
	}
	public void setSongId(String songId) {
		this.songId = songId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
