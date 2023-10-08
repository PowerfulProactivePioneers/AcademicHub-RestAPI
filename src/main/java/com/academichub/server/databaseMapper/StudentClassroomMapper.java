package com.academichub.server.databaseMapper;

import com.academichub.server.databaseSchema.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentClassroomMapper implements RowMapper<StudentClassRoomDB> {
	
	public StudentClassRoomDB mapRow(ResultSet resultSet, int i) throws SQLException {

		StudentClassRoomDB data = new StudentClassRoomDB();
		data.setId(resultSet.getString("id"));
		data.setCid(resultSet.getString("cid"));
		return data;
	}
}