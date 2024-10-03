package com.likewave.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likewave.entities.LikeWavePost;
import com.likewave.repositories.LikeWavePostRepository;

@Service
public class LikeWavePostServiceImplementation implements LikeWavePostService {

	@Autowired 
	LikeWavePostRepository repo;

	@Override
	public void createPost(LikeWavePost post) {

		repo.save(post);		
	}

	@Override
	public List<LikeWavePost> fetchAllPosts() {
		return repo.findAll();
	}

	@Override
	public void updateLikes(LikeWavePost post) {
		repo.save(post);
		
	}

	@Override
	public void updatePost(LikeWavePost post) {
		repo.save(post);
	}

	@Override
	public LikeWavePost getLikeWavePost(Long id) {
		return repo.findById(id).get();
	}
}
