package com.likewave.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.likewave.entities.LikeWavePost;
import com.likewave.entities.LikeWaveUser;
import com.likewave.services.LikeWavePostService;
import com.likewave.services.LikeWaveUserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LikeWavePostController {

	@Autowired
	LikeWavePostService service;
	
	@Autowired
	LikeWaveUserService userService;
	
	@PostMapping("/createPost")
	public String createPost(@RequestParam("caption") String caption,
			@RequestParam("photo") MultipartFile photo,
			Model model, HttpSession session

			) {
		
		String username=(String) session.getAttribute("username");
		LikeWaveUser user=userService.getUser(username);
		LikeWavePost post=new LikeWavePost();
		post.setUser(user);
		post.setCaption(caption);
		try {
			post.setPhoto(photo.getBytes());
			
		}catch(IOException e) {
			e.printStackTrace();		
			}
		
		service.createPost(post);
				
			List<LikeWavePost> posts = user.getPosts();
			if(posts == null) {
				posts = new ArrayList<LikeWavePost>();
			}
			posts.add(post);
			user.setPosts(posts);
			userService.updateUser(user);
					
			
		List<LikeWavePost> allPosts=service.fetchAllPosts();
		model.addAttribute("allPosts",allPosts);
		return "home";
	
}
	
	
	@PostMapping("/likePost")
	public String likePost(@RequestParam Long id, Model model) {
		LikeWavePost post=service.getLikeWavePost(id);
		post.setLikes(post.getLikes()+1);
		service.updateLikes(post);
		List<LikeWavePost> allPosts=service.fetchAllPosts();
		model.addAttribute("allPosts",allPosts);
		return "home";
	}
	
	
	@PostMapping("/addComment")
	public String addComment(@RequestParam Long id, 
			@RequestParam String comment, Model model) {
		System.out.println(comment);
		LikeWavePost post= service.getLikeWavePost(id);
				List<String> comments = post.getComments();
		if(comments == null) {
			comments = new ArrayList<String>();
		}
		comments.add(comment);
		post.setComments(comments);
		service.updatePost(post);
		
		List<LikeWavePost> allPosts = service.fetchAllPosts();
		model.addAttribute("allPosts", allPosts);
		return "home";
	}
	
	
	
}
