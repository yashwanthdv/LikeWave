package com.likewave.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.likewave.entities.LikeWavePost;
import com.likewave.entities.LikeWaveUser;
import com.likewave.services.LikeWavePostService;
import com.likewave.services.LikeWaveUserService;



@Controller
public class LikeWaveUserController {
	
		@Autowired
		LikeWaveUserService service;
		
		@Autowired
		LikeWavePostService postService;
		
		@PostMapping("/signUp")
		public String addLikeWaveUser(@ModelAttribute LikeWaveUser user,Model model)
		{
			
			String username=user.getUsername();
			String email=user.getEmail();
			boolean status=service.likeWaveUserExist(username,email);
			if(status==false) {
			service.addLikeWaveUser(user);	
			model.addAttribute("message","Sign-Up Sucessfull");
			}
			else{
			model.addAttribute("message","User already exists");
			}
			return "index";
		}
		
		@PostMapping("/login")
		public String login(@RequestParam String username,
				@RequestParam String password,
				Model model) {
			boolean status=service.validateUser(username,password);
			if(status==true) {
				List<LikeWavePost> allPosts = postService.fetchAllPosts();
				model.addAttribute("allPosts", allPosts);
				
				return "home";
			}
			else {
				model.addAttribute("message","Invalid username or password..!");
				return "index";
			}
		}

}
