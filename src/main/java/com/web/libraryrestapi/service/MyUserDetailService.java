package com.web.libraryrestapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.libraryrestapi.dao.UserRepository;
import com.web.libraryrestapi.model.MyUserDetail;
import com.web.libraryrestapi.model.User;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user =  userRepo.findByUserName(userName);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found "+userName));
		
		return user.map(MyUserDetail::new).get();
	}

}