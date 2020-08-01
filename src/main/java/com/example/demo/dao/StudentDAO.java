package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.demo.model.Student;

public interface StudentDAO {
	
	
    public Student createStudent(Student stud);

    public Student readStudent(String id);
 
    public Student updateStudent(Student stud);


    public void deleteStudent(String id);
}
