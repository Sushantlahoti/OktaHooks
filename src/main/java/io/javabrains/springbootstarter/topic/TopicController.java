package io.javabrains.springbootstarter.topic;

import java.awt.List;
import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TopicController {

	@RequestMapping("/topics")
	public java.util.List<Topic> getAllTopics(){
		
		return Arrays.asList(new Topic("spring", "Spring Fraemwork", "Spring Framework Description1"),	
				new Topic("java", "java Fraemwork", "Spring Framework Description2"),
				new Topic("javascript", "javacript Fraemwork", "Spring Framework Description3"));
		
	}
	
	
	
	}
	

