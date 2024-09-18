package com.likewave.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likewave.entities.LikeWaveUser;
import com.likewave.repositories.LikeWaveUserRepository;


@Service 
public class LikeWaveUserServiceImplementation implements LikeWaveUserService{

	
	@Autowired
	LikeWaveUserRepository repo;

	@Override
	public boolean likeWaveUserExist(String username, String email) {
		LikeWaveUser user1=repo.findByUsername(username);
		LikeWaveUser user2=repo.findByEmail(email);
		if(user1!=null || user2!=null) {
			return true;
		}
		
		return false;
	}

	@Override
	public void addLikeWaveUser(LikeWaveUser user) {
		repo.save(user);
	}

	@Override
	public boolean validateUser(String username, String password) {
		String dbPass=repo.findByUsername(username).getPassword();
		if(password.equals(dbPass)) {
			return true;
		}
		return false;
	}

	@Override
	public LikeWaveUser getUser(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public void updateUser(LikeWaveUser user) {
		repo.save(user);
	}
}
