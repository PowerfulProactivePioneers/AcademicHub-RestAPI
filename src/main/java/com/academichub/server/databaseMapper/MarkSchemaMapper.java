package com.academichub.server.databaseMapper;

import com.academichub.server.databaseSchema.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MarkSchemaMapper implements RowMapper<MarkSchema> {
	
	public MarkSchema mapRow(ResultSet resultSet, int i) throws SQLException {

		MarkSchema data = new MarkSchema();
		data.setRno(resultSet.getString("rno"));
		data.setCat1(resultSet.getInt("cat_1"));
		data.setCat2(resultSet.getInt("cat_2"));
		data.setCat3(resultSet.getInt("cat_3"));
		data.setAssignment1(resultSet.getInt("assignment_1"));
		data.setAssignment2(resultSet.getInt("assignment_2"));
		data.setAssignment3(resultSet.getInt("assignment_3"));
		
		return data;
	}
}