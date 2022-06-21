package com.naman.studentmanagement.service;

import java.util.List;

import com.naman.studentmanagement.entity.Student;

public interface studentService {
	List<Student> getAllStudents();
	Student saveStudent(Student student);
	Student updatestudent(Student student);
	Student getStudentId(long id );
}
