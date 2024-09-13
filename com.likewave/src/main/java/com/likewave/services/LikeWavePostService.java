package com.likewave.services;

import java.util.List;

import com.likewave.entities.LikeWavePost;

public interface LikeWavePostService {

	void createPost(LikeWavePost post);

	List<LikeWavePost> fetchAllPosts();

}
