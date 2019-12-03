package com.projectjava.playlist.edgeservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {
	private String id;
	private int userId;
	private String songId;

	public Playlist() {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId= userId;
	}
	public String getSongId() {
		return songId;
	}
	public void setSongId(String songId) {
		this.songId = songId;
	}
}
