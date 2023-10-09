package com.academichub.server.databaseManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.academichub.server.databaseMapper.ClassRoomMapper;
import com.academichub.server.databaseMapper.StudentClassroomMapper;
import com.academichub.server.databaseMapper.StudentFacultyMapper;
import com.academichub.server.databaseSchema.ClassRoomDB;
import com.academichub.server.databaseSchema.StudentClassRoomDB;
import com.academichub.server.databaseSchema.StudentFacultyDB;

@Repository
public class JDBCController {
	
	@Autowired
	private JdbcTemplate template;
	
	public List<StudentFacultyDB> getAllUsers() {
		try {
			return template.query("SELECT * FROM student_faculty", new StudentFacultyMapper());
		}
		catch (Exception e) {
			return null;
		}
	}
	
	//Insert any table values
	public String insert(String query) {
		try {
			template.update(query);
			return "success";
		}
		catch (Exception e) {
			return e.getCause().toString();
		}
	}
	
	// Find Specific User by email	
	public List<StudentFacultyDB> findUser(String email) {
		try {
			String queryString = String.format("SELECT * FROM student_faculty WHERE email = '%s'",email);
			System.out.println(queryString);
			return template.query(queryString, new StudentFacultyMapper());
		}
		catch (Exception e) {
			return null;
		}
	}
	
	// Find user by Id
	public List<StudentFacultyDB> findUserById(String query){
		try {
			return template.query(query, new StudentFacultyMapper());
		}
		catch (Exception e) {
			return null;
		}
	}
	
	// Find Specific Classroom
	public List<ClassRoomDB> findClassRoom(String query) {
		try {
			return template.query(query, new ClassRoomMapper());
		}
		catch (Exception e) {
			return null;
		}
	}
	
	// Find Already joined or not
	public Boolean checkAlreadyJoined(String query) {
		List<StudentClassRoomDB> res = template.query(query, new StudentClassroomMapper());
		if(res.isEmpty())
			return false;
		return true;
	}
	
	// Find Classrooms
	public List<ClassRoomDB> findClass(String query){
		return template.query(query, new ClassRoomMapper());
	}
	
	public List<StudentClassRoomDB> findClassforUser(String query){
		return template.query(query, new StudentClassroomMapper());
	}
}
