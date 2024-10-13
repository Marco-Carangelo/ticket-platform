package org.lesson.java.spring.ticketplatform.utils;

import org.lesson.java.spring.ticketplatform.model.User;
import org.lesson.java.spring.ticketplatform.service.UserService;
import org.springframework.security.core.Authentication;

public class AuthenticationUtil {
	
	//Method to get the currently logged user entity
	public static User getCurrentUser(Authentication authentication, UserService userService ){
		
		String username = authentication.getName();
		return userService.findUserByUsarname(username).get(); 
	}
	
	
	
	
}
