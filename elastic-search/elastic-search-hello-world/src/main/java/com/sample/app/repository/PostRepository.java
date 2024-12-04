package com.sample.app.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.Post;

@Repository
public interface PostRepository extends ElasticsearchRepository<Post, String> {

	List<Post> findByTitle(String title);
}
