package com.example.learnnn2.Mapper;

import com.example.learnnn2.Dto.StudentDto;
import com.example.learnnn2.Entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public static StudentDto maptoStudentDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getAddress());


    }
    public static Student maptoStudent(StudentDto studentDto){
        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail(),
                studentDto.getAddress());

    }
}
