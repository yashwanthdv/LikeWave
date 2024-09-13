package com.likewave.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.likewave.entities.LikeWavePost;
import com.likewave.services.LikeWavePostService;

@Controller
public class LikeWavePostController {

	@Autowired
	LikeWavePostService service;
	
	@PostMapping("/createPost")
	public String createPost(@RequestParam("caption") String caption,
			@RequestParam("photo") MultipartFile photo,
			Model model

			) {
		
		LikeWavePost post=new LikeWavePost();
		post.setCaption(caption);
		try {
			post.setPhoto(photo.getBytes());
			
		}catch(IOException e) {
			e.printStackTrace();		
			}
		
		service.createPost(post);
		List<LikeWavePost> allPosts=service.fetchAllPosts();
		model.addAttribute("allposts",allPosts);
		return "home";
	
}
}
