package com.web.libraryrestapi.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.web.libraryrestapi.model.News;

@Service
public class NewsService {
	
	@Autowired
	public RestTemplate restTemplate;
	
	public List<News> getTopics(){
		ResponseEntity<News[]> responseEntity = restTemplate.getForEntity("https://api.nytimes.com/svc/topstories/v2/science.json?api-key=O6ynAblsJ4DoLJz4Rl6y29SCEV8AZdU4", News[].class);
		News[] objects = responseEntity.getBody();
		return Arrays.asList(objects);
	}

}
