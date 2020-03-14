package com.web.libraryrestapi.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

	@Test
	public void test() {
		Book book = new Book();
		book.setId(1);
		book.setBookName("Chemistry");
		assertEquals(book.getId(), 1);
		assertEquals(book.getBookName(), "Chemistry");
	}

}
