package com.hibernate.Controller;

import com.hibernate.DAO.StudentDao;
import com.hibernate.Entity.Student;
import com.hibernate.Exception.StudentException;

import java.util.List;

public class StudentController extends StudentException {

    public static void main(String[] args) {

        StudentDao studentDao = new StudentDao();
        Student student = new Student("Rahul","KS","rahul...@gmail.com");
        Student anotherStudent = new Student("Rajesh", "kommula", "rajesh.kommula@yahoo.com");
        studentDao.persistME(student);
        studentDao.persistME(anotherStudent);

        List<Student> studentList = studentDao.getStudents();
        studentList.forEach(s->System.out.println("Email is "+ s.getEmail()));

        student.setEmail("rahul...@hotmail.com");
        studentDao.updateME(student);

        studentDao.deleteME(1);

        studentDao.getStudentWithID(2);
        studentDao.getStudentWithID(1);

    }



}
