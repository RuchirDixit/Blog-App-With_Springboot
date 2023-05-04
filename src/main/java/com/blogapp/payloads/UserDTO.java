package com.blogapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Ruchir Dixit
 *	This is Data Transfer Object whose properties we can provide to outside world, i.e. can be used in request and response
 */

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

	private int id;
	private String name;
	private String email;
	private String password;
	private String about;
}
