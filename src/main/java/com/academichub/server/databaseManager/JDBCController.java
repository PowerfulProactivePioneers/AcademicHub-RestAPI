package com.academichub.server.databaseManager;

import java.util.List;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.academichub.server.databaseMapper.ClassRoomMapper;
import com.academichub.server.databaseMapper.MarkSchemaMapper;
import com.academichub.server.databaseMapper.PostMapper;
import com.academichub.server.databaseMapper.StudentClassroomMapper;
import com.academichub.server.databaseMapper.StudentFacultyMapper;
import com.academichub.server.databaseSchema.ClassRoomDB;
import com.academichub.server.databaseSchema.MarkSchema;
import com.academichub.server.databaseSchema.Post;
import com.academichub.server.databaseSchema.StudentClassRoomDB;
import com.academichub.server.databaseSchema.StudentFacultyDB;
import com.academichub.server.responseClass.Marks;
import com.academichub.server.responseClass.UpdateAttendance;
import com.academichub.server.responseClass.UpdateMark;

import jakarta.persistence.Query;

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
			System.out.println("success");
			return "success";
		}
		catch (Exception e) {
			return e.getCause().toString();
		}
	}
	
	//Create tables
	public String createTable(String query) {
		try {
			template.execute(query);
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
	
	// Insert Post
	public int insertPost(String query1,String table) {
		try {
			template.update(query1);
			String querString = String.format("SELECT max(pid) FROM %s",table);
			int id = template.queryForObject(querString, Integer.class);
			System.out.println(id);
			return id;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());	
			return -1;
		}
	}
	
	// Retrieve all post
	public List<Post> getPost(String cid){
		String querString = String.format("SELECT * FROM %s_post ORDER BY pid DESC", cid);
		System.out.println(querString);
		return template.query(querString, new PostMapper());
	}
	
	//Get Students List
	public List<String> getStudentLists(String cid){
		String queryString = String.format("SELECT id FROM student_classroom WHERE cid = '%s'", cid);
		List<String> lst = template.queryForList(queryString, String.class);
		System.out.println(lst);
		return lst;
	}
	
	//Update Marks
	public String updateMarkSheet(UpdateMark data) {
		String query;
		String reString = "";
		System.out.println(data);
		for (Marks temp: data.getMarks()) {
			System.out.println(temp.GetMarks());
			query = String.format("UPDATE %s_marks SET %s = %d WHERE rno = '%s';", data.getCid().toLowerCase(),data.getExam().toLowerCase().replace(" ", "_"),temp.GetMarks(),temp.GetId());
			System.out.println(query);
			reString =  insert(query);
		}
		return reString;
	}
	
	//Update Attendance
	public String updateAttendance(UpdateAttendance data) {
		String query = String.format("INSERT INTO %s_attendance VALUES('%s','%s','%s');", data.getCid().toLowerCase(),data.getDate(),data.getPresent(),data.getAbsent());
		return insert(query);
	}
	
	//Get People
	public List<String> getPeople(String q1,String q2){
		List<String> lst = template.queryForList(q1,String.class);
		System.out.println(lst.toString());
		String facString = template.queryForObject(q2, String.class);
		lst.add(facString);
		return lst;
	}
	
	//Get Student Mark
	public MarkSchema getStudentMark(String query) {
		return template.query(query, new MarkSchemaMapper()).get(0);
	}
	
	//Get Student Attendance
	public List<String> getStudentAttendance(String query){
		return template.queryForList(query,String.class);
	}
}
