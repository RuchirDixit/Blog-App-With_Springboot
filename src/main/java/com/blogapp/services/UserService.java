package com.blogapp.services;

import java.util.List;
import com.blogapp.payloads.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user,int userId);
	UserDTO getUserById(int userId);
	List<UserDTO> getAllUsers();
	void deleteUser(int userId);
}
