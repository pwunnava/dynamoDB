package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;


//This can be accessed as http://localhost:8080
@RestController
public class StudentRestController {

    private StudentService userCrudService;

    @Autowired
    public StudentRestController(StudentService userCrudService) {
        this.userCrudService = userCrudService;
    }

    //The following can be accessed as http://localhost:8080/addStudent/
    @RequestMapping(value = "/addStudent", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity createUser() {
    	Student s=new Student();
    	s.setId("9");
    	s.setName("Amitabh Bacchan"); 
    	s.setAddress("777  Newark Blvd");
    	s.setCity("Fremont");
    	s.setZip("94560");
    	
        try {
            Student response = userCrudService.createStudent(s);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    //The following can be accessed as http://localhost:8080/students/1
    @RequestMapping(value = "/students/{userId}", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity readUser(@PathVariable String userId) {
        try {
            Student response = userCrudService.readStudent(userId);
            System.out.println("The student retrieve is:->" + response.getName());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/users", produces = {"application/json"}, method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody Student stud) {
        try {
            Student response = userCrudService.updateStudent(stud);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/users/{userId}", produces = {"application/json"}, method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String userId) {
        try {
            userCrudService.deleteStudent(userId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
