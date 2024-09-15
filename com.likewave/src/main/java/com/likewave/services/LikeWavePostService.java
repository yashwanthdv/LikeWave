package com.likewave.services;

import java.util.List;

import com.likewave.entities.LikeWavePost;

public interface LikeWavePostService {

	void createPost(LikeWavePost post);

	List<LikeWavePost> fetchAllPosts();

	void updateLikes(LikeWavePost post);

	void updatePost(LikeWavePost post);

	LikeWavePost getLikeWavePost(Long id);


}
