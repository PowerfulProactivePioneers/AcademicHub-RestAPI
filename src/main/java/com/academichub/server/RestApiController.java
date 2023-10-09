package com.academichub.server;

import com.academichub.server.responseClass.*;
import com.academichub.server.databaseManager.*;
import com.academichub.server.databaseSchema.ClassRoomDB;
import com.academichub.server.databaseSchema.StudentClassRoomDB;
import com.academichub.server.databaseSchema.StudentFacultyDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
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
		return new Status(res);
	}
	
	@PostMapping("/get-user-classrooms")
	public List<ClassRoomDB> getUserClassRooms(@RequestBody UserIDType data) {
		if(data.getType().equals("Faculty")) {
			String queryString = String.format("SELECT * FROM classroom WHERE fac_id = '%s'", data.getId());
			List<ClassRoomDB> res = controller.findClass(queryString);
			return res;
		}
		else {
			String queryString = String.format("SELECT * FROM student_classroom WHERE id = '%s'", data.getId());
			List<StudentClassRoomDB> res = controller.findClassforUser(queryString);
			if(res.isEmpty()) {
				return new ArrayList<ClassRoomDB>();
			}
			String query = "SELECT * FROM CLASSROOM WHERE";
			for (StudentClassRoomDB item : res) {
				query+=" cid = '"+item.getCid()+"' OR";
			}
			query = query.substring(0, query.length()-3);
			System.out.println(query);
			List<ClassRoomDB> classdata = controller.findClass(query);
			return classdata;
		}
	}
}