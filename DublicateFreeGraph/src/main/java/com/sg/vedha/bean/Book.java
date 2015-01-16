/**
 * 
 */
package com.sg.vedha.bean;

/**
 * @author Sivaguru
 *
 */
public class Book {

	private String bookName;
	private String keyword;	
	
	public Book(String bookName, String keyword) {
		super();
		this.bookName = bookName;
		this.keyword = keyword;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", keyword=" + keyword + "]";
	}	
}
