package com.academichub.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

@Component
public class Schedule {
	
	@Autowired
	RestApiController restApi;
	
	@Scheduled(fixedRate = 600000) 
    public void myTask() {
        // Your code to be executed every 10 minutes goes here
        restApi.getAllUser();
    }
}
