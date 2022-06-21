package com.naman.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.JedisClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.naman.studentmanagement.entity.Student;
import com.naman.studentmanagement.service.studentService;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Controller
public class studentController {	
		
	@Bean
	public RedisTemplate<Long, Student> redisTemplate(RedisConnectionFactory connectionFactory) {
	    RedisTemplate<Long, Student> template = new RedisTemplate<>();
	    template.setConnectionFactory(connectionFactory);
	    return template;
	}
	
	@Autowired
	private RedisTemplate<Long, Student> redisTemplate;

	public void save(long id , Student student) {
	    redisTemplate.opsForValue().set(id, student);
	}
	
	
	public Student findById(Long id) {
	    return redisTemplate.opsForValue().get(id);
	}

	
	private studentService studentSer ;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	
	
	public studentController(studentService studentSer) {
		super();
		this.studentSer = studentSer;
	} 
	
	//	Handler 
	@GetMapping("/students")
	public String listStudents(Model model ) {
			System.out.println("LOG : "+getJdbcTemplate().getDataSource());
			System.out.println("Redis Output :" + redisTemplate.randomKey());
		model.addAttribute("students", studentSer.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
//		Create Student object to hold data 
		Student student = new Student();
		model.addAttribute("student", student);
		return "createStudent";
	}
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentSer.saveStudent(student)	;
		
		return "redirect:/students";
	}
	@GetMapping("/students/edit/{id}")
	public String updateList(@PathVariable long id, Model model) {
		model.addAttribute("student", studentSer.getStudentId(id));
		return "editStudent";
	}
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student , Model model) {
		Student existingStudent = studentSer.getStudentId(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		save(id,existingStudent);
		studentSer.updatestudent(existingStudent);
		
		
		return "redirect:/students";
		
	}
	
	
	
	
	
}
