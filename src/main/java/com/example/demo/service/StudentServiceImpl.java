package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentDAO;
import com.example.demo.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDAO studentCrudDao;

    @Autowired
    public StudentServiceImpl(StudentDAO studCrudDao) {
        this.studentCrudDao = studCrudDao;
    }

    @Override
    public Student createStudent(Student stud) {
        return studentCrudDao.createStudent(stud);
    }

    @Override
    public Student readStudent(String id) {
        return studentCrudDao.readStudent(id);
    }

    @Override
    public Student updateStudent(Student stud) {
        return studentCrudDao.updateStudent(stud);
    }

    @Override
    public void deleteStudent(String id) {
        studentCrudDao.deleteStudent(id);
    }
}
