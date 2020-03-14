package com.web.libraryrestapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.web.libraryrestapi.model.Book;

public interface LibraryDao extends CrudRepository<Book, Integer>{

}
