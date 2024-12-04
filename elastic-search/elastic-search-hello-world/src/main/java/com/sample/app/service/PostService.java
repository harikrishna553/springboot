package com.sample.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dto.PostRequestDto;
import com.sample.app.dto.PostResponseDto;
import com.sample.app.entity.Post;
import com.sample.app.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	Post convert(PostRequestDto dto) {
		Post post = new Post();
		post.setContent(dto.getContent());
		post.setTags(dto.getTags());
		post.setTitle(dto.getTitle());
		return post;
	}

	PostResponseDto convert(Post post) {
		PostResponseDto resp = new PostResponseDto();
		resp.setContent(post.getContent());
		resp.setId(post.getId());
		resp.setTags(post.getTags());
		resp.setTitle(post.getTitle());
		return resp;
	}

	// Create or update a post
	public PostResponseDto savePost(PostRequestDto postRequestDto) {
		Post post = postRepository.save(convert(postRequestDto));
		return convert(post);
	}

	// Get a post by ID
	public PostResponseDto getPostById(String id) {
		Optional<Post> post = postRepository.findById(id);
		if (post.isEmpty()) {
			throw new RuntimeException("Post Not Found");
		}

		return convert(post.get());
	}

	public List<PostResponseDto> getAllPosts() {
		Iterable<Post> iterablePosts = postRepository.findAll();
		List<PostResponseDto> posts = new ArrayList<>();
		iterablePosts.forEach(post -> {
			posts.add(convert(post));
		}); // Add each post to the list
		return posts;
	}

	// Delete a post by ID
	public void deletePost(String id) {
		postRepository.deleteById(id);
	}

	// Search posts by title
	public List<PostResponseDto> searchPostsByTitle(String title) {
		List<Post> posts = postRepository.findByTitle(title);

		List<PostResponseDto> postsResponse = new ArrayList<>();
		posts.forEach(post -> {
			postsResponse.add(convert(post));
		}); // Add each post to the list
		return postsResponse;
	}
}
