package com.academichub.server;

import java.util.List;

import org.aspectj.weaver.patterns.VoidArrayFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	@Autowired
	public DBFunctions dbfn;
	
	@GetMapping("/sample")
	public String sample() {
		return "sample";
	}
	
	@PostMapping("/create-user")
	public Status create(@RequestBody StudentFacultyDB data) {
		System.out.println("New");
		System.out.println(data.toString());
		System.out.println(data.getSection());
		dbfn.createUser(data);
		Status st = new Status("success");
		return st;
	}
	
	@PostMapping("/getuser")
	public String getUser(@RequestBody String email) {
		System.out.println(email);
		List<StudentFacultyDB> res = dbfn.findUser(email);
		if(!res.isEmpty()) {
			System.out.println(1);
			return "Exists "+ res.get(0).getName();
		}
		else {
			System.out.println(2);
			return "Does not Exists";
		}
	}
}