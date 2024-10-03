package com.likewave.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.likewave.entities.LikeWavePost;
import com.likewave.entities.LikeWaveUser;
import com.likewave.services.LikeWavePostService;
import com.likewave.services.LikeWaveUserService;

import jakarta.servlet.http.HttpSession;



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
				Model model,
				HttpSession session) {
			if(username.isEmpty()||password.isEmpty()){
				model.addAttribute("message","No credentials entered");
				return "index";
			}
			boolean status=service.validateUser(username,password);
			
			
			if(status==true) {
				List<LikeWavePost> allPosts = postService.fetchAllPosts();
				session.setAttribute("username", username);
				model.addAttribute("session",session);
				model.addAttribute("allPosts", allPosts);
				
				return "home";
			}
			else {
				model.addAttribute("message","Invalid password..!");
				return "index";
			}
		}
			 
				
			 
			
		
		@PostMapping("/updateProfile")
		public String updateProfile(
				@RequestParam String dob,
				@RequestParam String bio,
				@RequestParam String gender,
				@RequestParam String city,
				@RequestParam String college,
				@RequestParam String linkedIn,
				@RequestParam String gitHub,
				@RequestParam MultipartFile photo
				, HttpSession session,
				Model model
				) {
			
			String username = (String) session.getAttribute("username");
		
			LikeWaveUser user=service.getUser(username);
			
			user.setDob(dob);
			user.setGender(gender);
			user.setCity(city);
			user.setBio(bio);
			user.setCollege(college);
			user.setLinkedIn(linkedIn);
			user.setGitHub(gitHub);
			try {						
				user.setProfilePic(photo.getBytes());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			service.updateUser(user);
			model.addAttribute("user", user);
			
			
			return "myProfile";
		}
		
		@GetMapping("/forgotPassword")
		public String showForgotPasswordForm() {
		    return "forgotPassword"; 
		}
		
		
		@PostMapping("/resetPassword")
		public String resetPassword(@RequestParam String username, @RequestParam String newPassword, Model model) {
		    boolean status = service.resetUserPassword(username, newPassword);
		    if (status) {
		        model.addAttribute("message", "Password reset successfully!");
		    } else {
		        model.addAttribute("error", "Failed to reset password. User may not exist.");
		        return "forgotPassword";
		    }
		    return "index"; 
		}
		
		

}
