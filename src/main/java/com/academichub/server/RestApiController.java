package com.academichub.server;

import com.academichub.server.responseClass.*;

import requestClass.ClassDate;
import requestClass.ClassMark;

import com.academichub.server.databaseManager.*;
import com.academichub.server.databaseSchema.Attendance;
import com.academichub.server.databaseSchema.ClassRoomDB;
import com.academichub.server.databaseSchema.MarkSchema;
import com.academichub.server.databaseSchema.Post;
import com.academichub.server.databaseSchema.StudentClassRoomDB;
import com.academichub.server.databaseSchema.StudentFacultyDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {
	
	@Autowired
	JDBCController controller;
	
	@GetMapping("/")
	public List<StudentFacultyDB> getAllUser() {
		return controller.getAllUsers();
	}
	
	@PostMapping("/create-user")
	public Status create(@RequestBody StudentFacultyDB data) {
		String query = String.format("INSERT INTO student_faculty VALUES ('%s','%s','%s','%s','%s','%s','%c')", data.getId(),data.getName(),data.getUid(),data.getDept(),data.getEmail(),data.getType(),data.getSection());
		String res = controller.insert(query);
		return new Status(res);
	}
	
	@PostMapping("/getuser")
	public StudentFacultyDB getUser(@RequestBody String email) {
		email = email.replace("\"", "");
		System.out.println(email+email.length());
		List<StudentFacultyDB> user = controller.findUser(email);
		System.out.println(user);
		if(!user.isEmpty()) {
			return user.get(0);
		}
		else {
			return new StudentFacultyDB();
		}
	}
	
	@PostMapping("/create-classroom")
	public Status createClassroom(@RequestBody ClassRoomDB room) {
		String queryString = String.format("INSERT INTO classroom VALUES ('%s','%s','%s','%s','%s','%c')", room.getCid(),room.getCcode(),room.getCname(),room.getFac_id(),room.getAllowed_dept(),room.getAllowed_section());
		String resString = controller.insert(queryString);
		if(resString.contains("trailing junk") || resString.equals("success")) {
		System.out.println("1");
		queryString = String.format("CREATE TABLE %s_attendance (date varchar(20) PRIMARY KEY,Present varchar(1000),Absent varchar(1000))", room.getCid());
		System.out.println(queryString);
		resString = controller.createTable(queryString);
		System.out.println(resString);
		queryString = String.format("CREATE TABLE %s_marks (rno varchar(20) PRIMARY KEY,CAT_1 Integer,Assignment_1 Integer,CAT_2 Integer,Assignment_2 Integer,CAT_3 Integer,Assignment_3 Integer)", room.getCid());
		resString = controller.createTable(queryString);
		System.out.println(resString);
		queryString = String.format("CREATE TABLE %s_post (pid SERIAL PRIMARY KEY,title varchar(100),description varchar(500),files varchar,assignment BOOLEAN,due_date varchar(15))", room.getCid());
		resString = controller.createTable(queryString);
		System.out.println(resString);
		}
		return new Status(resString);
	}
	
	@PostMapping("/join-classroom")
	public Status joinClassRoom(@RequestBody StudentClassRoomDB data) {
		String querString = String.format("SELECT * FROM classroom WHERE cid='%s'", data.getCid());
		List<ClassRoomDB> classroom = controller.findClassRoom(querString);
		if(classroom.isEmpty())
			return new Status("Invalid Classroom");
		
		String query1 = String.format("SELECT * FROM student_faculty WHERE id='%s'", data.getId());
		System.out.println(query1);
		List<StudentFacultyDB> student = controller.findUserById(query1);
		if(student.isEmpty())
			return new Status("Invalid User");
		
		String userDept = student.get(0).getDept();
		char userSection = student.get(0).getSection();
		String classDept = classroom.get(0).getAllowed_dept();
		char classSection = classroom.get(0).getAllowed_section();
		
		if(!classDept.equals("ALL")) {
			if(classSection != 'N') {
				if((userDept.equals(classDept)) && (userSection == classSection)) {
					
				}	
				else {
					return new Status("Only "+classroom.get(0).getAllowed_dept()+"-"+classroom.get(0).getAllowed_section()+" students are allowed");
				}
			}
			else {
				if(!userDept.equals(classDept))
					return new Status("Only "+classDept+" students are allowed");
			}
		}
		
		String queryString = String.format("SELECT * FROM student_classroom WHERE id = '%s' and cid = '%s';", data.getId(),data.getCid());
		if(controller.checkAlreadyJoined(queryString))
			return new Status("Already Joined");
		
		String query = String.format("INSERT INTO student_classroom VALUES ('%s','%s');", data.getId(),data.getCid());
		String res = controller.insert(query);
		query = String.format("INSERT INTO %s_marks (rno) VALUES ('%s')", data.getCid().toLowerCase(),data.getId());
		res = controller.insert(query);
		return new Status(res);
	}
	
	@PostMapping("/get-user-classrooms")
	public List<ClassRoomDB> getUserClassRooms(@RequestBody UserIDType data) {
		System.out.println(data.getType()+data.getId());
		if(data.getType().equals("Faculty")) {
			String queryString = String.format("SELECT * FROM classroom WHERE fac_id = '%s'", data.getId());
			List<ClassRoomDB> res = controller.findClass(queryString);
			return res;
		}
		else {
			String query = String.format("SELECT * FROM CLASSROOM WHERE cid in (SELECT cid FROM student_classroom WHERE id = '%s')",data.getId());
			List<ClassRoomDB> classdata = controller.findClass(query);
			System.out.println(classdata);
			return classdata;
		}
	}
	
	
	@PostMapping("/create-post")
	public Status createPost(@RequestBody Post data) {
		System.out.println(data.toString());
		String query = String.format("INSERT INTO %s_post (title,description,files,assignment,due_date) VALUES('%s','%s','%s','%b','%s')", data.getCid(),data.getTitle(),data.getDesc(),data.getFiles(),data.isAssignment(),data.getDate());
		int id = controller.insertPost(query,data.getCid()+"_post");
		if (id == -1) {
			return new Status("fail");
		}
		String st = "success";
		if(data.isAssignment()) {
			query = String.format("CREATE TABLE %s_assignment_%d(regno varchar(20),files varchar)",data.getCid(),id);
			st = controller.createTable(query);
		}
		return new Status(st);
	}
	
	@PostMapping("/get-post")
	public List<Post> retreivePost(@RequestBody String cid){
		return controller.getPost(cid.toLowerCase().replace("\"", ""));
	}
	
	@PostMapping("/get-students-list")
	public List<String> getStudentList(@RequestBody String cid){
		System.out.println(123+cid);
		return controller.getStudentLists(cid.replace("\"", ""));
	}

	@PostMapping("/update-marks")
	public Status updateMarks(@RequestBody UpdateMark data) {
		String res = controller.updateMarkSheet(data);
		return new Status(res);
	}
	
	@PostMapping("/update-attendance")
	public Status updateAttendance(@RequestBody UpdateAttendance data) {
		String str = controller.updateAttendance(data);
		return new Status(str);
	}
	
	@PostMapping("/get-people")
	public List<String> getPeople(@RequestBody String data) {
		data = data.replace("\"", "");
		System.out.println(data);
		List<String> lst;
		String queryString = String.format("SELECT name FROM student_faculty WHERE id in (SELECT id FROM student_classroom WHERE cid = '%s');", data);
		String queryString1 = String.format("SELECT name FROM student_faculty WHERE id in (SELECT fac_id FROM classroom WHERE cid = '%s');", data);
		lst = controller.getPeople(queryString,queryString1);
		return lst;
	}
	
	@PostMapping("/student-mark")
	public List<MarkSchema> getStudentMarks(@RequestBody StudentMark data){
		List<MarkSchema> lst = new ArrayList<>();
		String queryString = String.format("SELECT * FROM %s_marks WHERE rno = '%s'", data.getCid().toLowerCase(),data.getId());
		lst.add(controller.getStudentMark(queryString));
		queryString = String.format("SELECT max(rno) as rno,avg(cat_1) as cat_1,avg(cat_2) as cat_2,avg(cat_3) as cat_3,avg(assignment_1) as assignment_1,avg(assignment_2) as assignment_2,avg(assignment_3) as assignment_3 FROM %s_marks", data.getCid().toLowerCase());
		lst.add(controller.getStudentMark(queryString));
		queryString = String.format("SELECT max(rno) as rno,max(cat_1) as cat_1,max(cat_2) as cat_2,max(cat_3) as cat_3,max(assignment_1) as assignment_1,max(assignment_2) as assignment_2,max(assignment_3) as assignment_3 FROM %s_marks", data.getCid().toLowerCase());
		lst.add(controller.getStudentMark(queryString));
		return lst;
	}
	
	@PostMapping("/student-attendance")
	public List<AttendanceList> getStudentAttendance(@RequestBody StudentMark data) {
		String key = "%" + data.getId() + "%";
		String queryString = String.format("SELECT date FROM %s_attendance WHERE present ILIKE '%s'", data.getCid().toLowerCase(),key);
		String queryString1 = String.format("SELECT date FROM %s_attendance WHERE absent ILIKE '%s'", data.getCid().toLowerCase(),key);
		return controller.getStudentAttendance(queryString,queryString1);
	}
	
	@PostMapping("/student-all-attendance")
	public List<AttendanceReport> getAllAttendance(@RequestBody String id){
		id = id.replace("\"","");
		return controller.findAllAttendance(id);
	}
	
	@PostMapping("/class-wise-attendance")
	public List<ClassDateResponse> getClassWiseAttendance(@RequestBody ClassDate data) {
		String queryString = String.format("SELECT present FROM %s_attendance WHERE date = '%s'", data.getCid(),data.getDate());
		String queryString1 = String.format("SELECT absent FROM %s_attendance WHERE date = '%s'", data.getCid(),data.getDate());
		return controller.getClassDateWiseAttendance(queryString,queryString1);
	}
	
	@PostMapping("/class-wise-marks")
	public List<ClassMarkResponse> getClassWiseMarkForSpecificExam(@RequestBody ClassMark data) {
		String queryString = String.format("SELECT rno,%s as mark FROM %s_marks",data.getExam(),data.getCid());
		return controller.getClassWiseMarkForExam(queryString);
	}

	
}