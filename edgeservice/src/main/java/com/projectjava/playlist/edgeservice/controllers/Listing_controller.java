/*************************************************************
** mappings:
** Listing content:
** 		String titel;
** 		String titel;
** 		int userId;
** 		String songId;
** 		int rating;
**
** GET:
**		- /listings/ratinguser/ID
**		- /listings/ratingsong/ID
 *      - /listings/songtitle/TITLE
 *      - /listings/songid/ID
 *** 	- /listings/playlists
 ** 	- /listings/playlist/ID
** POST: (create)
 **		- /listings/useraddsong/ID		## BODY:
 **											title: STRING
 **											artist: STRING
 **											cover: STRING
 **											album: STRING
 **											duration: STRING
 **		- /listings/useraddrating/ID		## BODY:
 **											rating: INT
 **											userid: STRING
 **											songid: STRING
 **		- /listings/useraddplaylist/ID		## BODY: TODO
 **											int: INT
 **											userid: STRING
 **											songid: ARRAY[STRING]
** PUT: (edit)
**		- /listings/user/ID
** 			- /playlist/user/ID/song/ID
** DELETE:
**		- /listings/user/ID/song/ID
** 			- /playlist/user/ID/song/ID
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
	private Song getSong(String songid){
		Song song = new Song();
		if(songid.matches("[0-9]+")) {
/*
            String str = restTemplate.getForObject("https://api.deezer.com/track/"+songid, String.class);
            JSONObject json = null;
            try {
                json = new JSONObject(str);
            JSONObject artist  = (JSONObject)json.get("artist");
            JSONObject album = (JSONObject)json.get("album");

            song.setId((String) json.get("id"));
			song.setTitel((String) json.get("title"));
            song.setArtist((String) artist.get("name"));
            song.setGenre("DEEZER GENRE");
            song.setCover((String) json.get("preview"));
            song.setAlbum((String) album.get("title"));
            song.setDuration((String) json.get("duration"));
            song.setUserId(1);
            } catch (JSONException e) {
                song = new Song("0","BROKE","BROKE","BROKE","BROKE","BROKE","BROKE",0);
            }
            */
		}
		else{
			song= restTemplate.getForObject(URL_SONG+ "songs/search/findSongById?songId=" + songid , Song.class);

		}
		return song;
	}
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("ratinguser/{userId}")
	public List<ListingItem> getListingItemsByUserId(@PathVariable("userId") int userId) {
		GenericResponseWrapper wrapper = restTemplate.getForObject(URL_RATING+ "ratings/search/findRatingsByUserId?userId=" + userId, GenericResponseWrapper.class);
		List<Rating> ratings = objectMapper.convertValue(wrapper.get_embedded().get("ratings"), new TypeReference<List<Rating>>() { });
		List<ListingItem> returnList = new ArrayList<>();
		for (Rating rating: ratings) {
			Song song= restTemplate.getForObject(URL_SONG+ "songs/search/findSongById?songId=" + rating.getSongId() , Song.class);
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
                Song song= restTemplate.getForObject(URL_SONG+ "songs/search/findSongById?songId=" + rating.getSongId() , Song.class);

                returnList.add(new ListingItem(song.getTitel(), rating.getRating(), rating.getUserId(),song.getId()));
            }
        }
        return returnList;
    }
	@GetMapping("songid/{songId}")
	public Song getSongById(@PathVariable("songId") String songId) {
		Song song= restTemplate.getForObject(URL_SONG+ "songs/search/findSongById?songId=" + songId , Song.class);
		return song;
	}
	@GetMapping("songtitle/{songTitle}")
	public List<Song> getSongsByTitle(@PathVariable("songTitle") String songTitle) {
		GenericResponseWrapper wrapper = restTemplate.getForObject(URL_SONG+ "songs/search/findSongsByTitleContaining?title=" + songTitle, GenericResponseWrapper.class);
		List<Song> songs = objectMapper.convertValue(wrapper.get_embedded().get("songs"), new TypeReference<List<Song>>() { });
		return songs;
	}
	/*
 *** 	- /listings/playlists
 ** 	- /listings/playlist/ID
	*/
	@GetMapping("playlists/{userid}")
	public List<Playlist> getPlaylists(@PathVariable("userid") int userid) {
		GenericResponseWrapper wrapper = restTemplate.getForObject(URL_PLAYLIST+ "playlists/", GenericResponseWrapper.class);
		List<Playlist> playlists = objectMapper.convertValue(wrapper.get_embedded().get("playlists"), new TypeReference<List<Playlist>>() { });
		for (Playlist playlist: playlists) {
			playlist.setSongs(new ArrayList<Song>());
			for (String songid: playlist.getSongId()) {
				{
					Song song= restTemplate.getForObject(URL_SONG+ "songs/search/findSongById?songId=" + songid , Song.class);
					playlist.getSongs().add(song);
				}
			}
		}
		return playlists;
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
	/*
	@PutMapping("/user/{userId}")
	public ResponseEntity<String> putListingItemsByUserId(@PathVariable("userId") String userId, @RequestBody ListingItem listingItem) {
		List<HttpMessageConverter<?>> list = new ArrayList<>();
		list.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(list);

		Song song = restTemplate.getForObject(URL_SONG+"songs/search/findSongById?songid="+ listingItem.getSongId(), Song.class);
		Rating rating = restTemplate.getForObject(URL_RATING+"ratings/search/findRatingByUserIdAndMovieId?userid="+userId+"&songid="+song.getId(), Rating.class);
		rating.setRating(listingItem.getRating());
		restTemplate.put(URL_RATING+"ratings/"+rating.getId(), rating, String.class);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/user/{userId}/song/{songId}")
	public ResponseEntity deleteListingItemsByUserIdAndSongId(@PathVariable("userId") String userId, @PathVariable("songId") String songId){

		Song song = restTemplate.getForObject(URL_SONG+"songs/search/findSongById?songid="+ songId, Song.class);

        Rating rating = restTemplate.getForObject(URL_RATING+"ratings/search/findRatingByUserIdAndSongId?userid=" + userId + "&songid=" + song.getId(), Rating.class);

        restTemplate.delete(URL_RATING+"ratings/" + rating.getId());

        return ResponseEntity.ok().build();
    }
	 */
}
