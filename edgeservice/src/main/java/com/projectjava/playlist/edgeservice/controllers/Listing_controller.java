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
** 			- /playlist/user/ID
** POST: (create)
**		- /listings/useraddsong/ID		## BODY:
**											title: STRING
 **											artist: STRING
 **											cover: STRING
 **											album: STRING
 **											duration: STRING
** 			- /playlist/user/ID
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/listings")
public class Listing_controller {
	
	private static String URL_RATING = "http://rating/";
	private static String URL_SONG = "http://song/";

	private int getLoggedInUserId() {
		return 1;
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
			Song song= restTemplate.getForObject(URL_SONG+ "songs/search/findSongById?songId=" + rating.getSongId(), Song.class);

			//Song song = restTemplate.getForObject(url, Song.class);
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
				Song song= restTemplate.getForObject(URL_SONG+ "songs/search/findSongById?songId=" + rating.getSongId(), Song.class);
				returnList.add(new ListingItem(song.getTitel(), rating.getRating(), rating.getUserId(),song.getId()));
			}
		}
		return returnList;
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
