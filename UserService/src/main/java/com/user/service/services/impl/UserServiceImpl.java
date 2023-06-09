package com.user.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repositories.UserRepository;
import com.user.service.responsedto.ApiResponse;
import com.user.service.services.UserService;

@Service
@SuppressWarnings("unchecked")
public class UserServiceImpl<T> implements UserService<T> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public T createUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		User savedUser = this.userRepository.save(user);
		return (T) savedUser;
	}

	@Override
	public T updateUser(User user, String userId) {
		User user2 = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));
		user2.setUserEmail(user.getUserEmail());
		user2.setUserName(user.getUserName());
		user2.setUserPassword(user.getUserPassword());
		return (T) this.userRepository.save(user2);
	}

	@Override
	public T deleteUser(String userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));
		this.userRepository.delete(user);
		return (T) new ApiResponse("User Deleted Successfully !!!", true, HttpStatus.OK);
	}

	@Override
	public T getAllUsers() {
		List<User> allUsers = this.userRepository.findAll();
		return (T) allUsers;
	}

	@Override
	public T getUserById(String userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));
		return (T) user;
	}

	@Override
	public T updloadProfile(String userId, MultipartFile file) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));
		
		
		
		return null;
	}
	
}
