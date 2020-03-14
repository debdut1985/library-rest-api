package com.web.libraryrestapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.libraryrestapi.dao.LibraryDao;
import com.web.libraryrestapi.model.Book;

@Service
public class LibraryService {

	@Autowired
	private LibraryDao libraryDao;
	
	
	public List<Book> getAllBooks(){
		
		List<Book> books = new ArrayList<>();
		libraryDao.findAll().forEach(books::add);
		return books;
	}
	
	public Book getBookById(int id){
		
		return libraryDao.findById(id).get();
	}
	
	public void save(Book book) {
		libraryDao.save(book);
	}
}
