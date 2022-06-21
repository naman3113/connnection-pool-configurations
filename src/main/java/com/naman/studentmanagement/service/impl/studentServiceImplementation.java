package com.naman.studentmanagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.naman.studentmanagement.entity.Student;
import com.naman.studentmanagement.repository.studentRepository;
import com.naman.studentmanagement.service.studentService;
@Service
public class studentServiceImplementation implements studentService{
	
		private studentRepository studentrepo ; 
		
		
		public studentServiceImplementation(studentRepository studentrepo) {
			super();
			this.studentrepo = studentrepo;
			
		}


		@Override
		public List<Student> getAllStudents() {
			return studentrepo.findAll();
		}


		@Override
		public Student saveStudent(Student student) {
			return studentrepo.save(student);
		}


		@Override
		public Student updatestudent(Student student) {
			// TODO Auto-generated method stub
			return studentrepo.save(student);
		}


		@Override
		public Student getStudentId(long id) {
			// TODO Auto-generated method stub
			return studentrepo.findById(id).get();
		}
}
