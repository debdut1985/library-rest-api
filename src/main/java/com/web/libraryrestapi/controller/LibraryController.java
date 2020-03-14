package com.web.libraryrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.libraryrestapi.model.AuthenticationResponse;
import com.web.libraryrestapi.model.Book;
import com.web.libraryrestapi.service.LibraryService;
import com.web.libraryrestapi.service.MyUserDetailService;
import com.web.libraryrestapi.util.JWTUtil;

@RestController
@RequestMapping("/api")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private AuthenticationManager authenticateManager;
	
	@Autowired
	private MyUserDetailService userDetailService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping("/authenticate/{user}/{pass}")
	public ResponseEntity<?> createAuthToken(@PathVariable("user") String user, @PathVariable("pass") String password) throws Exception{
		
		try {
			authenticateManager.authenticate(
					new UsernamePasswordAuthenticationToken(user, password)
					);
		}catch(BadCredentialsException e) {
			throw new Exception("User not found");
		}
		final UserDetails userDetails = userDetailService.loadUserByUsername(user);
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@GetMapping("/user")
	public String greetingUser(){
		return "<h2>Welcome User</h2>";
	}
	
	@GetMapping("/admin")
	public String greetingAdmin(){
		return "<h2>Welcome Admin</h2>";
	}
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return libraryService.getAllBooks();
	}
	
	@GetMapping("/book/{id}")
	public Book getBookById(@PathVariable("id") int id){
		return libraryService.getBookById(id);
	}
	
	@PostMapping("/book")
	public void saveBook(@RequestBody Book book){
		Book bookToSave = new Book();
		bookToSave.setBookName(book.getBookName());
		libraryService.save(bookToSave);
	}
}
