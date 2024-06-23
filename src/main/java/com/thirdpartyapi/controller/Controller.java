package com.thirdpartyapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thirdpartyapi.model.Post;
import com.thirdpartyapi.service.ConsumeService;

@RestController
@RequestMapping("/api")
public class Controller {

	ConsumeService service;

	public Controller(ConsumeService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Post>> getPost() {
		return new ResponseEntity<List<Post>>(service.getPosts(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id) {
		return new ResponseEntity<Post>(service.getPostById(id), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Post> insertPost(@RequestBody Post data) {
		return new ResponseEntity<Post>(service.post(data), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Post> putPost(@PathVariable Long id, @RequestBody Post data) {
		return new ResponseEntity<Post>(service.putPost(id, data), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Post> deletePost(@PathVariable Long id) {
		return new ResponseEntity<Post>(service.deletePost(id), HttpStatus.OK);
	}
}
