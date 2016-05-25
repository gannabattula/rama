package com.rama.test;

import org.springframework.stereotype.Service;

import com.rama.BookType;

@Service("testService")
public class TestServiceImpl implements TestService{

	
	public BookType getDetails() {
		// retrieve book information based on the id supplied in the formal argument
 
        BookType bookType = new BookType();
        bookType.setBookId(2522);
        bookType.setBookName("rrrrr");
        bookType.setAuthor("");
        bookType.setCategory("sss");
		return bookType;
	}
}
