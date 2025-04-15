package com.example.learnnn2.Service;

import com.example.learnnn2.Dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long StudentId);
    List<StudentDto> getAllStudents();
    StudentDto updateStudent(Long StudentId, StudentDto updatedStudentDto);
    void deleteStudent(Long StudentId);


}
