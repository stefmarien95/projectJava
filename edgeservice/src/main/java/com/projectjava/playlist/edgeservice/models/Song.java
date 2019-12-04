package com.projectjava.playlist.edgeservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
	private String id;
	private String title;
	private String artist;
	private String genre;
	private String cover;
	private String album;
	private String duration;
	private int userId;

	public Song() {}
	public Song(String id, String title, String artist, String genre, String cover, String album, String duration, int userId) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.cover = cover;
		this.album = album;
		this.duration = duration;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitel() {
		return title;
	}
	public void setTitel(String titel) {
		this.title = titel;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId= userId;
	}
}
