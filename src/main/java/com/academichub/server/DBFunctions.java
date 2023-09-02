package com.academichub.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class DBFunctions {

	@Autowired
	private StudentFacultyRepository sfrep;
	
	public void createUser(StudentFacultyDB data) {
		sfrep.save(data);
//		return "Hello";
	}
	
	public List<StudentFacultyDB> findUser(String email) {
		return sfrep.findByEmail(email);
	}
}
