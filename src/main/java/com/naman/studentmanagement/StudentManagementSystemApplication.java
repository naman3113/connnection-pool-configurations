package com.naman.studentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.naman.studentmanagement.entity.Student;
import com.naman.studentmanagement.repository.studentRepository;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}
	@Autowired
	private studentRepository studRepo ; 
	
	
	@Override
	public void run(String... args) throws Exception {
		Student student1 = new Student("ABC", "103","abc@123" );
		studRepo.save(student1);
		
		Student student2 = new Student("234", "abc","234@abc" );
		studRepo.save(student2);
		
	}

}
