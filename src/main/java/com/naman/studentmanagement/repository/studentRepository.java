package com.naman.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naman.studentmanagement.entity.Student;

public interface studentRepository extends JpaRepository<Student, Long>{
		
		
}
