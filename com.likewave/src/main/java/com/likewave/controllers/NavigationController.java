package com.likewave.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.likewave.entities.LikeWavePost;
import com.likewave.services.LikeWavePostService;

@Controller
public class NavigationController {
	
	@Autowired
	LikeWavePostService PostService;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/openSignUp")
	public String openSignUp() {
		return "signUp";
	}
	
	@GetMapping("/openCreatePost")
	public String openCreatePost() {
		return "createPost";
	}
	
	@GetMapping("/goHome")
	public String login(Model model)	{
			List<LikeWavePost> allPosts = PostService.fetchAllPosts();
			model.addAttribute("allPosts", allPosts);
			return "home";
	}
	
}
