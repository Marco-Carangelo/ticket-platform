package org.lesson.java.spring.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.lesson.java.spring.ticketplatform.model.Role;
import org.lesson.java.spring.ticketplatform.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails{
	
	private final Integer id;
	private final String username;
	private final String password;
	private final Set<GrantedAuthority> authorities;
	
	public DatabaseUserDetails(User user) {
		
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		
		authorities = new HashSet<GrantedAuthority>();
		for(Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
	

}