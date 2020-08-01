package com.example.demo.service;

import com.example.demo.model.Student;

public interface StudentService {


    public Student createStudent(Student stud);   
  
    public Student readStudent(String id);
     
    public Student updateStudent(Student stud);
 
    public void deleteStudent(String id);
}
