package com.academichub.server.databaseMapper;

import com.academichub.server.databaseSchema.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentFacultyMapper implements RowMapper<StudentFacultyDB> {
	
	public StudentFacultyDB mapRow(ResultSet resultSet, int i) throws SQLException {

		StudentFacultyDB data = new StudentFacultyDB();
		data.setId(resultSet.getString("id"));
		data.setDept(resultSet.getString("dept"));
		data.setEmail(resultSet.getString("email"));
		data.setName(resultSet.getString("name"));
		data.setSection(resultSet.getString("section").charAt(0));
		data.setType(resultSet.getString("type"));
		data.setUid(resultSet.getString("uid"));
		return data;
	}
}