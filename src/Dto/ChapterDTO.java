package Dto;

import java.util.*;
import Model.*;

public class ChapterDTO {
	
	private Integer chaperId;
	private String chapterName;
	private List<Book>book;
	
	public Integer getChaperId() {
		return chaperId;
	}
	
	public void setChaperId(Integer chaperId) {
		this.chaperId = chaperId;
	}
	
	public String getChapterName() {
		return chapterName;
	}
	
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	
	public List<Book> getBook() {
		return book;
	}
	
	public void setBook(List<Book> book) {
		this.book = book;
	}
	
}
