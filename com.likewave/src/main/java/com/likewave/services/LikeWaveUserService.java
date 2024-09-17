package com.likewave.services;

import com.likewave.entities.LikeWaveUser;

public interface LikeWaveUserService {

	boolean likeWaveUserExist(String username, String email);

	void addLikeWaveUser(LikeWaveUser user);

	boolean validateUser(String username, String password);

	
}