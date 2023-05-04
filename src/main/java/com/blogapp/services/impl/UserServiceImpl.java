package com.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.UserDTO;
import com.blogapp.repositories.UserRepo;
import com.blogapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDTO createUser(UserDTO user) {
		User uEntity = dtoToUserEntity(user);
		User saveduser = userRepo.save(uEntity);
		return userEntityToDTO(saveduser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, int userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		
		User updatedUser = userRepo.save(user);
		return userEntityToDTO(updatedUser);
	}

	@Override
	public UserDTO getUserById(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return userEntityToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDTO> userDTOList = users.stream().map(user->userEntityToDTO(user)).collect(Collectors.toList());
		return userDTOList;
	}

	@Override
	public void deleteUser(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		userRepo.delete(user);

	}
	
	private User dtoToUserEntity(UserDTO user) {
		User userEntity = new User();
		userEntity.setId(user.getId());
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setAbout(user.getAbout());
		userEntity.setPassword(user.getPassword());
		return userEntity;
	}
	
	private UserDTO userEntityToDTO(User userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setName(userEntity.getName());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setAbout(userEntity.getAbout());
		userDTO.setPassword(userEntity.getPassword());
		return userDTO;
	}

}
