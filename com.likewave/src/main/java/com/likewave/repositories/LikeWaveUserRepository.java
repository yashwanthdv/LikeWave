package com.likewave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likewave.entities.LikeWaveUser;

public interface LikeWaveUserRepository extends JpaRepository<LikeWaveUser,Long> {

	
	LikeWaveUser findByUsername(String username);

	LikeWaveUser findByEmail(String email);
}
