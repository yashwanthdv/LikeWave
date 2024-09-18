package com.likewave.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.likewave.entities.LikeWavePost;
import com.likewave.entities.LikeWaveUser;
import com.likewave.services.LikeWavePostService;
import com.likewave.services.LikeWaveUserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {
	
	@Autowired
	LikeWaveUserService service;
	
	
	@Autowired
	LikeWavePostService postService;

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
	public String goHome(Model model)	{
			List<LikeWavePost> allPosts = postService.fetchAllPosts();
			model.addAttribute("allPosts", allPosts);
			return "home";
	}
	
	@GetMapping("/openMyProfile")
	public String openMyProfile(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		LikeWaveUser user = service.getUser(username);
		model.addAttribute("user", user);
		return "myProfile";
	}
	
	@GetMapping("/openEditProfile")
	public String openEditProfile() {
		return "editProfile";
	}
	
	
}
