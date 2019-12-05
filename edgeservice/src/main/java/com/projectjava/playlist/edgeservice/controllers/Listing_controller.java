/*************************************************************
** mappings:
** Listing content:
** 		String titel;
** 		int userId;
** 		String songId;
** 		int rating;
** GET:
**		- /listings/ratinguser/ID		<< List<listingItem>, ID=userId
**		- /listings/ratingsong/ID		<< List<listingItem>, ID=songId
 *      - /listings/songs/1				<< List<Song>, ALLES, 1 moet erachter of het marcheert ni
 *      - /listings/songtitle/TITLE		<< List<Song>, TITLE=songTitle
 *      - /listings/songid/ID			<< Song, ID=songId
 *** 	- /listings/playlistsuser/ID	<< List<Playlists>, ID=userId
 *** 	- /listings/playlistid/ID		<< Playlists, ID=playlistId
** POST: (create)
 **		- /listings/useraddsong/		## BODY:
 **											title: STRING
 **											artist: STRING
 **											cover: STRING
 **											album: STRING
 **											duration: STRING
 **											userId: INT
 **		- /listings/useraddrating/		## BODY:
 **											rating: INT
 **											userId: STRING
 **											songId: STRING
 **		- /listings/useraddplaylist/		## BODY:
 **											name: STRING
** PUT: (edit)
 **		- /listings/songaddplaylist/		## BODY:
 **											playlistId: INT
 **											songId: INT
** DELETE:
** 		- /listings/songdeleteplaylist/{playlistId}/{songId}")
** 		- /listings/deleteplaylist/{playlistId}
*************************************************************/
package com.projectjava.playlist.edgeservice.controllers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectjava.playlist.edgeservice.models.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/listings")
public class Listing_controller {
	
	private static String URL_RATING = "http://rating/";
	private static String URL_SONG = "http://song/";
	private static String URL_PLAYLIST = "http://playlist/";

	private int getLoggedInUserId() {
		return 1;
	}
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	private Song getSong(Integer songId) {
	    Song song = new Song();
	    // FIXME: deftig detectere of song lokaal zit of in deezer
		if(songId < 200) {
			song = restTemplate.getForObject(URL_SONG+ "songs/"+songId, Song.class);
		}
		else{
			song.setId(songId.toString());
		}
		return song;
	    /*
	    // breken bij tijdsnood
		GenericResponseWrapper wrapper = restTemplate.getForObject(URL_SONG+ "songs/", GenericResponseWrapper.class);
		List<Song> songs = objectMapper.convertValue(wrapper.get_embedded().get("songs"), new TypeReference<List<Song>>() { });
		for (Song song: songs) {
			if(song.getId().equals(songId))
			{
				return song;
			}
		}

		wrapper = restTemplate.getForObject(URL_SONG+ "songs/search/findSongByUserId?userId="+songId, GenericResponseWrapper.class);
		songs = objectMapper.convertValue(wrapper.get_embedded().get("songs"), new TypeReference<List<Song>>() { });
		return songs.get(0);
	    */
	}

	@GetMapping("ratinguser/{userId}")
	public List<ListingItem> getListingItemsByUserId(@PathVariable("userId") int userId) {
		GenericResponseWrapper wrapper = restTemplate.getForObject(URL_RATING+ "ratings/search/findRatingsByUserId?userId=" + userId, GenericResponseWrapper.class);
		List<Rating> ratings = objectMapper.convertValue(wrapper.get_embedded().get("ratings"), new TypeReference<List<Rating>>() { });
		List<ListingItem> returnList = new ArrayList<>();
		for (Rating rating: ratings) {
			Song song = getSong(Integer.parseInt(rating.getSongId()));
			returnList.add(new ListingItem(song.getTitel(), rating.getRating(), rating.getUserId(),song.getId()));
		}
		return returnList;
	}
	@GetMapping("ratingsong/{songId}")
    public List<ListingItem> getListingItemsBySongId(@PathVariable("songId") String songId) {
        GenericResponseWrapper wrapper = restTemplate.getForObject(URL_RATING+ "ratings/", GenericResponseWrapper.class);
        List<Rating> ratings = objectMapper.convertValue(wrapper.get_embedded().get("ratings"), new TypeReference<List<Rating>>() { });
        List<ListingItem> returnList = new ArrayList<>();
        for (Rating rating: ratings) {
            if(rating.getSongId().equals(songId))
            {
				Song song = getSong(Integer.parseInt(rating.getSongId()));

                returnList.add(new ListingItem(song.getTitel(), rating.getRating(), rating.getUserId(),song.getId()));
            }
        }
        return returnList;
    }
	@GetMapping("/songs/{songId}")
	public List<Song> getSongs() {
		GenericResponseWrapper wrapper = restTemplate.getForObject(URL_SONG+ "songs/", GenericResponseWrapper.class);
		List<Song> songs = objectMapper.convertValue(wrapper.get_embedded().get("songs"), new TypeReference<List<Song>>() { });
		return songs;
	}
	@GetMapping("songid/{songId}")
	public Song getSongById(@PathVariable("songId") String songId) {
		Song song = getSong(Integer.parseInt(songId));
		return song;
	}
	@GetMapping("songtitle/{songTitle}")
	public List<Song> getSongsByTitle(@PathVariable("songTitle") String songTitle) {
		GenericResponseWrapper wrapper = restTemplate.getForObject(URL_SONG+ "songs/search/findSongsByTitleContaining?title=" + songTitle, GenericResponseWrapper.class);
		List<Song> songs = objectMapper.convertValue(wrapper.get_embedded().get("songs"), new TypeReference<List<Song>>() { });
		return songs;
	}
	@GetMapping("playlistsuser/{userid}")
	public List<Playlist> getPlaylists(@PathVariable("userid") int userid) {
		GenericResponseWrapper wrapper = restTemplate.getForObject(URL_PLAYLIST+ "playlists/", GenericResponseWrapper.class);
		List<Playlist> playlists = objectMapper.convertValue(wrapper.get_embedded().get("playlists"), new TypeReference<List<Playlist>>() { });
		List<Playlist> returnlist = new ArrayList<Playlist>();
		for (Playlist playlist: playlists) {
			if(playlist.getUserId() == userid) {
				playlist.setSongs(new ArrayList<Song>());
				for (String songid: playlist.getSongId()) {
					Song song = getSong(Integer.parseInt(songid));
					playlist.getSongs().add(song);
				}
				returnlist.add(playlist);
			}
		}
		return returnlist;
	}
	@GetMapping("playlistid/{playlistId}")
	public Playlist getPlaylistById(@PathVariable("playlistId") String playlistId) {
		Playlist playlist = restTemplate.getForObject(URL_PLAYLIST+ "playlists/" + playlistId , Playlist.class);
		playlist.setSongs(new ArrayList<Song>());
		for (String songid: playlist.getSongId()) {
			Song song = getSong(Integer.parseInt(songid));
			playlist.getSongs().add(song);
		}
		return playlist;
	}
	@PostMapping("/useraddrating/")
	public ResponseEntity<String> postUserAddRating(@RequestBody Rating rating) {
        rating.setUserId(getLoggedInUserId());
// manueel POST data zetten, spring fokt dees
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put("rating",rating.getRating());
        map.put("userId",rating.getUserId());
        map.put("songId",rating.getSongId());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map,headers);

        ResponseEntity<String> result = restTemplate.postForEntity(URL_RATING +"ratings/", entity, String.class);
        return ResponseEntity.ok().build();
    }

	@PostMapping("/useraddsong/")
	public ResponseEntity<String> postUserAddSong(@RequestBody Song song) {
		song.setUserId(getLoggedInUserId());
// manueel POST data zetten, spring fokt dees
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("title",song.getTitel());
		map.put("artist",song.getArtist());
		map.put("cover",song.getCover());
		map.put("album",song.getAlbum());
		map.put("duration",song.getDuration());
		map.put("userId",song.getUserId());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map,headers);

		ResponseEntity<String> result = restTemplate.postForEntity(URL_SONG+"songs/", entity, String.class);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/useraddplaylist/")
	public ResponseEntity<String> postUserAddPlaylist(@RequestBody Playlist playlist) {
		playlist.setUserId(getLoggedInUserId());
// manueel POST data zetten, spring fokt dees
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("name",playlist.getName());
		map.put("userId",playlist.getUserId());
		map.put("songId", new ArrayList<String>());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map,headers);

		ResponseEntity<String> result = restTemplate.postForEntity(URL_PLAYLIST+"playlists/", entity, String.class);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/songaddplaylist/")
	public ResponseEntity<String> putSongAddPlaylist(@RequestBody PlaylistItem item) {
		Playlist playlist = restTemplate.getForObject(URL_PLAYLIST+ "playlists/" + item.getPlaylistId(), Playlist.class);
		playlist.getSongId().add(item.getSongId());
// manueel POST data zetten, spring fokt dees
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("name",playlist.getName());
		map.put("userId",playlist.getUserId());
		map.put("songId",playlist.getSongId());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map,headers);

		restTemplate.put(URL_PLAYLIST+"playlists/" + playlist.getId(), entity, String.class);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("songdeleteplaylist/{playlistId}/{songId}")
	public ResponseEntity deleteSongFromPlaylist(@PathVariable("playlistId") String playlistId, @PathVariable("songId") String songId) {
		Playlist playlist = restTemplate.getForObject(URL_PLAYLIST+ "playlists/" + playlistId, Playlist.class);
		playlist.getSongId().remove(songId);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("name",playlist.getName());
		map.put("userId",playlist.getUserId());
		map.put("songId",playlist.getSongId());

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map,headers);

		restTemplate.put(URL_PLAYLIST+"playlists/" + playlist.getId(), entity, String.class);

		return ResponseEntity.ok().build();
	}
	@DeleteMapping("deleteplaylist/{playlistId}")
	public ResponseEntity deletePlaylist(@PathVariable("playlistId") String playlistId) {
		restTemplate.delete(URL_PLAYLIST+ "playlists/"+playlistId);
		return ResponseEntity.ok().build();
	}
}
