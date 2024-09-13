package com.likewave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likewave.entities.LikeWavePost;

public interface LikeWavePostRepository extends JpaRepository<LikeWavePost,Long> {

}
