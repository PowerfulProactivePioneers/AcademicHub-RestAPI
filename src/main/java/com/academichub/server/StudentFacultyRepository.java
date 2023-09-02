package com.academichub.server;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface StudentFacultyRepository extends JpaRepository<StudentFacultyDB,Long>{

	List<StudentFacultyDB> findByEmail(String email);
}
