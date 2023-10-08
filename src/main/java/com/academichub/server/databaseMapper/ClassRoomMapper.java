package com.academichub.server.databaseMapper;

import com.academichub.server.databaseSchema.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ClassRoomMapper implements RowMapper<ClassRoomDB> {
	
	public ClassRoomDB mapRow(ResultSet resultSet, int i) throws SQLException {

		ClassRoomDB data = new ClassRoomDB();
		data.setCid(resultSet.getString("cid"));
		data.setCcode(resultSet.getString("ccode"));
		data.setCname(resultSet.getString("cname"));
		data.setFac_id(resultSet.getString("fac_id"));
		data.setAllowed_dept(resultSet.getString("allowed_dept"));
		data.setAllowed_section(resultSet.getString("allowed_section").charAt(0));
		return data;
	}
}