package com.thirdpartyapi.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.thirdpartyapi.model.Post;

@Service
public class ConsumeService {

	private final RestTemplate restTemplate;

	public ConsumeService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
	StringBuilder base_URL = new StringBuilder(BASE_URL);
	String POST = "/posts";
	String POSTBYID = "/posts/";

	public List<Post> getPosts() {
		HttpEntity<Void> httpEntity = new HttpEntity<>(getHeader());
		String post_url = base_URL.append(POST).toString();
		ResponseEntity<List> response = restTemplate.exchange(post_url, HttpMethod.GET, httpEntity, List.class);
		return response.getBody();
	}

	public Post getPostById(Long id) {
		HttpEntity<Void> httpEntity = new HttpEntity<>(getHeader());
		String post_by_id_url = base_URL.append(POSTBYID).append(id).toString();
		ResponseEntity<Post> response = restTemplate.exchange(post_by_id_url, HttpMethod.GET, httpEntity, Post.class);
		return response.getBody();
	}

	public Post post(Post post) {
		String post_url = base_URL.append(POST).toString();
		HttpEntity<Post> httpEntity = new HttpEntity<>(post, getHeader());
		ResponseEntity<Post> response = restTemplate.exchange(post_url, HttpMethod.POST, httpEntity, Post.class);
		return response.getBody();
	}

	public Post putPost(Long id, Post post) {
		String put_url = base_URL.append(POSTBYID).append(id).toString();
		HttpEntity<Post> httpEntity = new HttpEntity<>(post, getHeader());
		ResponseEntity<Post> response = restTemplate.exchange(put_url, HttpMethod.PUT, httpEntity, Post.class);
		return response.getBody();
	}

	public Post deletePost(Long id) {
		String delete_url = base_URL.append(POSTBYID).append(id).toString();
		HttpEntity<Post> httpEntity = new HttpEntity<>(getHeader());
		ResponseEntity<Post> response = restTemplate.exchange(delete_url, HttpMethod.DELETE, httpEntity, Post.class);
		return response.getBody();
	}

	public HttpHeaders getHeader() {
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.setContentType(MediaType.APPLICATION_JSON);
		return header;
	}

}
