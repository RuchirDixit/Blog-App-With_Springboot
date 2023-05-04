package com.blogapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogapp.payloads.UserDTO;
import com.blogapp.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Add user
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
		UserDTO createdUserDTO =  userService.createUser(userDTO);
		return new ResponseEntity<>(createdUserDTO,HttpStatus.CREATED);
	}
	
	//Get user
	@GetMapping("/fetchAllUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> allUsersDTO = userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(allUsersDTO,HttpStatus.OK);
	}
	
	//Get user By Id
	@GetMapping("/fetchUser/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer id){
		UserDTO fetcheduser = userService.getUserById(id);
		return new ResponseEntity<>(fetcheduser,HttpStatus.OK);
	}
	 
	
	//Update User
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable Integer userId){
		UserDTO updatedUserDTO = userService.updateUser(userDTO, userId);
		return new ResponseEntity<UserDTO>(updatedUserDTO,HttpStatus.OK);
	}
	
	//Delete User
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return new ResponseEntity(Map.of("Message","User Deleted Successfully."),HttpStatus.OK);
	}
}
