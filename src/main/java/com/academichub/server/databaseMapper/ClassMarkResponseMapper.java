package com.academichub.server.databaseMapper;

import com.academichub.server.databaseSchema.*;
import com.academichub.server.responseClass.ClassMarkResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ClassMarkResponseMapper implements RowMapper<ClassMarkResponse> {
		
		public ClassMarkResponse mapRow(ResultSet resultSet, int i) throws SQLException {

			ClassMarkResponse data = new ClassMarkResponse();
			data.setRno(resultSet.getString("rno"));
			data.setMark(resultSet.getInt("mark"));
			return data;
		}
}
