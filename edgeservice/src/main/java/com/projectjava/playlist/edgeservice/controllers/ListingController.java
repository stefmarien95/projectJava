/*************************************************************
** mappings:
** GET:
**		- /listings/user/ID
**		- /listings/song/ID
**
** POST: (create)
**		- /listings/user/ID
** PUT: (edit)
**		- /listings/user/ID
** DELETE:
**		- /listings/user/ID/song/ID
*************************************************************/
package com.projectjava.playlist.edgeservice.controllers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectjava.playlist.edgeservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("user/{userId}")
	public List<ListingItem> getListingItemsByUserId(@PathVariable("userId") int userId) {
		List<ListingItem> returnList = new ArrayList<>();
		GenericResponseWrapper wrapper = restTemplate.getForObject("http://rating/ratings/search/findRatingsByUserId?userid="+userId, GenericResponseWrapper.class);
		List<Rating> ratings = objectMapper.convertValue(wrapper.get_embedded().get("ratings"), new TypeReference<List<Rating>>() {});
		for(Rating rating: ratings) {
			Song song = restTemplate.getForObject("http://song/songs/search/findSongById?songid="+ rating.getSongId(), Song.class);
			returnList.add(new ListingItem(song.getTitel(), rating.getRating(), song.getId()));
		}
		return returnList;
	}
	@GetMapping("/song/{songId}")
	public List<ListingItem> getListingItemsBySongId(@PathVariable("songId") int songId) {
		List<ListingItem> returnList = new ArrayList<>();
		GenericResponseWrapper wrapper = restTemplate.getForObject("http://song/songs/search/findSongById?songid=/"+songId, GenericResponseWrapper.class);
		List<Song> songs = objectMapper.convertValue(wrapper.get_embedded().get("songs"), new TypeReference<List<Song>>() {});
		for(Song song: songs) {
			Rating rating = restTemplate.getForObject("http://rating/ratings/search/findRatingsBySongId?songid="+songId, Rating.class);
			returnList.add(new ListingItem(song.getTitel(), rating.getRating(), song.getId()));
		}
		return returnList;
	}
	@PostMapping("/user/{userId}")
	public ResponseEntity<String> postListingItemsByUserId( @PathVariable("userId") int userId, @RequestBody ListingItem listingItem) {
		List<HttpMessageConverter<?>> list = new ArrayList<>();
		list.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(list);

		Song song = restTemplate.getForObject("http://song/songs/search/findSongById?songid="+ listingItem.getSongId(), Song.class);
		Rating rating = new Rating(userId, song.getId(), listingItem.getRating());
		ResponseEntity<String> result = restTemplate.postForEntity("http://rating-service/ratings/", rating, String.class);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/user/{userId}")
	public ResponseEntity<String> putListingItemsByUserId(@PathVariable("userId") int userId, @RequestBody ListingItem listingItem) {
		List<HttpMessageConverter<?>> list = new ArrayList<>();
		list.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(list);

		Song song = restTemplate.getForObject("http://song/songs/search/findSongById?songid="+ listingItem.getSongId(), Song.class);
		Rating rating = restTemplate.getForObject("http://rating-service/ratings/search/findRatingByUserIdAndMovieId?userid="+userId+"&songid="+song.getId(), Rating.class);
		rating.setRating(listingItem.getRating());
		restTemplate.put("http://rating-service/ratings/"+rating.getId(), rating, String.class);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/user/{userId}/song/{songId}")
	public ResponseEntity deleteListingItemsByUserIdAndSongId(@PathVariable("userId") int userId, @PathVariable("songId") int songId){

		Song song = restTemplate.getForObject("http://song/songs/search/findSongById?songid="+ songId, Song.class);

        Rating rating = restTemplate.getForObject("http://rating-service/ratings/search/findRatingByUserIdAndSongId?userid=" + userId + "&songid=" + song.getId(), Rating.class);

        restTemplate.delete("http://rating-service/ratings/" + rating.getId());

        return ResponseEntity.ok().build();
    }
}
