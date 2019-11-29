package com.projectjava.playlist.edgeservice.models;

import lombok.Data;

@Data
public class ListingItem {
	private String titel;
	private int userId;
	private int songId;
	private int rating;

	public ListingItem() {}
	public ListingItem(String titel, int rating, int songId) {
		this.titel = titel;
		this.rating = rating;
		this.songId = songId;
	}
	public ListingItem(String titel, int rating, int userId, int songId) {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
	    this.userId = userId;
    }
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
