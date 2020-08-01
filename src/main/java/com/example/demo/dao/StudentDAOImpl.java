package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.demo.model.Student;

@Component
	public class StudentDAOImpl implements StudentDAO {

	    private DynamoDBMapper dynamoDBMapper;

	    @Autowired
	    public StudentDAOImpl(DynamoDBMapper dynamoDBMapper) {
	        this.dynamoDBMapper = dynamoDBMapper;
	    }

	    @Override
	    public Student createStudent(Student stud) {
	        dynamoDBMapper.save(stud);
	        return stud;
	    }

	    @Override
	    public Student readStudent(String id) {
	        return dynamoDBMapper.load(Student.class, id);
	    }

	    @Override
	    public Student updateStudent(Student stud) {
	        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
	        expectedAttributeValueMap.put("id", new ExpectedAttributeValue(new AttributeValue().withS(stud.getId())));
	        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
	        dynamoDBMapper.save(stud, saveExpression);
	        return stud;
	    }

	    @Override
	    public void deleteStudent(String id) {
	        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
	        expectedAttributeValueMap.put("id", new ExpectedAttributeValue(new AttributeValue().withS(id)));
	        DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression().withExpected(expectedAttributeValueMap);
	        Student stu = new Student();
	        stu.setId(id);
	        dynamoDBMapper.delete(stu, deleteExpression);
	    }
	}

