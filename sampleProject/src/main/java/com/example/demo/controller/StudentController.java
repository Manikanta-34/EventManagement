package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.Studentrepo;
import com.example.demo.model.Student;
@Controller
public class StudentController {
	@Autowired
	Studentrepo repo;
	@GetMapping(path="/all")
	  public @ResponseBody Iterable<Student> getAllUsers() {
	    // This returns a JSON or XML with the users
	    return repo.findAll();
	  }
	
	
//	 @PostMapping(path="/add") // Map ONLY POST Requests
//	  public @ResponseBody String addNewUser (@RequestParam String name
//	      , @RequestParam String email) {
//	    
//
//	    Student n = new Student();
//	    n.setName(name);
//	    n.setEmail(email);
//	    repo.save(n);
//	    return "Saved";
//	  }
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser (@RequestBody Student student) {
	    repo.save(student);
	    return "Saved";
	  }
	@GetMapping(path="/FindById/{id}")
	  public @ResponseBody Optional<Student> getUser(@PathVariable int id) {
		return repo.findById(id);
	   
	}
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		try {
		repo.deleteById(id);
		return new ResponseEntity<String>( "Deleted", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>( "No Data founded...",HttpStatus.BAD_REQUEST);
		}
		
	}
		
	
	

}
