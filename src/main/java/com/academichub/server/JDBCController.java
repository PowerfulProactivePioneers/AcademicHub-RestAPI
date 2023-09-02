package com.academichub.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCController {
	
	@Autowired
	private JdbcTemplate template;
	
	public String insert(String query) {
		try {
			template.update(query);
			return "success";
		}
		catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}
	
	public String create(String query) {
		try {
			template.execute(query);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}
}
