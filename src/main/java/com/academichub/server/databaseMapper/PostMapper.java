package com.academichub.server.databaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.academichub.server.databaseSchema.ClassRoomDB;
import com.academichub.server.databaseSchema.Post;

public class PostMapper implements RowMapper<Post>{
	public Post mapRow(ResultSet resultSet, int i) throws SQLException {

		Post data = new Post();
		data.setPid(resultSet.getInt("pid"));
		data.setTitle(resultSet.getString("title"));
		data.setDesc(resultSet.getString("description"));
		data.setFiles(resultSet.getString("files"));
		data.setDate(resultSet.getString("due_date"));
		data.setAssignment(Boolean.getBoolean( resultSet.getString("assignment")));
		data.setPosted_date(resultSet.getString("posted_date"));
		return data;
	}

}
