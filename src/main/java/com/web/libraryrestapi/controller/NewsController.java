package com.web.libraryrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.libraryrestapi.model.News;
import com.web.libraryrestapi.service.NewsService;

@RestController
public class NewsController {
	
	@Autowired
	public NewsService newsService;
	
	@GetMapping("/api/topstories")
	public List<News> getTopStories(){
		return newsService.getTopics();
	}

}
