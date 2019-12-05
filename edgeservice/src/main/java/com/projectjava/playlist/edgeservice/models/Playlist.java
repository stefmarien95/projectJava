package com.projectjava.playlist.edgeservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {
	private String id;
	private int userId;
	private String name;
	private List<String> songId;
	private List<Song> songs;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	public List<String> getSongId() {
		return songId;
	}
	public void setSongId(List<String> songid) {
		this.songId = songid;
	}

}
