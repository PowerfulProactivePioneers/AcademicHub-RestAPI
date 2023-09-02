package com.academichub.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JDBCCommandLineRunner implements CommandLineRunner{
	
	@Autowired
	public JDBCController controller;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Started");
	}
	
	public String insert(String query) {
		return controller.insert(query);
	}
}
