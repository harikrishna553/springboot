package com.sample.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.PostRequestDto;
import com.sample.app.dto.PostResponseDto;
import com.sample.app.service.PostService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@Operation(summary = "Create or update a post", description = "Create a new post or update an existing post by providing a post object.")
	@PostMapping
	public ResponseEntity<PostResponseDto> createOrUpdatePost(@RequestBody PostRequestDto post) {
		System.out.println("Post to be saved: " + post);
		PostResponseDto savedPost = postService.savePost(post);
		return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
	}

	@Operation(summary = "Get all posts", description = "Retrieve a list of all posts.")
	@GetMapping
	public List<PostResponseDto> getAllPosts() {
		return postService.getAllPosts();
	}

	@Operation(summary = "Get post by ID", description = "Retrieve a post by its ID.")
	@GetMapping("/{id}")
	public ResponseEntity<PostResponseDto> getPostById(@PathVariable String id) {
		PostResponseDto postResponseDto = postService.getPostById(id);
		return ResponseEntity.ok(postResponseDto);
	}

	@Operation(summary = "Delete a post", description = "Delete a post by its ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable String id) {
		postService.deletePost(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Search posts by title", description = "Search posts by their title.")
	@GetMapping("/search")
	public List<PostResponseDto> searchPostsByTitle(@RequestParam String title) {
		return postService.searchPostsByTitle(title);
	}
}
