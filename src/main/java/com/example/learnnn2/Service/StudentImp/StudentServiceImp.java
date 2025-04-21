package com.example.learnnn2.Service.StudentImp;

import com.example.learnnn2.Dto.StudentDto;
import com.example.learnnn2.Entity.Student;
import com.example.learnnn2.Exeception.NotFound;
import com.example.learnnn2.Mapper.StudentMapper;
import com.example.learnnn2.Repo.StudentRepo;
import com.example.learnnn2.Service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImp implements StudentService {

    private StudentRepo studentRepo;
    @Autowired
    public StudentServiceImp(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
// creating a student
    public StudentDto createStudent(StudentDto studentDto) {
        Student student= StudentMapper.maptoStudent(studentDto);
        Student savedStudent=studentRepo.save(student);
        return StudentMapper.maptoStudentDto(savedStudent);
    }

    @Override
    /// geting by id
//    @Cacheable(cacheNames = "students", key = "#StudentId")
    public StudentDto getStudentById(Long StudentId) {
Student student = studentRepo.findById(StudentId)
        .orElseThrow(()-> new NotFound("Student not found with id: "+StudentId));
        return StudentMapper.maptoStudentDto(student);
    }

    @Override
    ///  geting all students
//    @Cacheable(value ="students")
    public List<StudentDto> getAllStudents() {
        // paganaton
      //  Pageable pageable=PageRequest.of(pageNo ,pageSize);
        List<Student> students=studentRepo.findAll();

        return students.stream().map((student)->StudentMapper.maptoStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long StudentId, StudentDto updatedStudentDto) {
        Student student = studentRepo.findById(StudentId)
                .orElseThrow(() -> new NotFound("Student of that id is not found:" + StudentId));

        if (updatedStudentDto.getFirstName() != null) {
            student.setFirstName(updatedStudentDto.getFirstName());
        }
        if (updatedStudentDto.getLastName() != null) {
            student.setLastName(updatedStudentDto.getLastName());
        }
        if (updatedStudentDto.getEmail() != null) {
            student.setEmail(updatedStudentDto.getEmail());
        }
        if (updatedStudentDto.getAddress() != null) {
            student.setAddress(updatedStudentDto.getAddress());
        }

        Student updatedStudentObj = studentRepo.save(student);
        return StudentMapper.maptoStudentDto(updatedStudentObj);
    }
    ///  update students
//    @CachePut( cacheNames ="students", key= "#id")
//    public StudentDto updateStudent(Long StudentId, StudentDto updatedStudentDto) {
//        Student student = studentRepo.findById(StudentId)
//                .orElseThrow(() -> new NotFound("Studdent of that id is not found:" + StudentId));
//      if(updatedStudentDto.getFirstName() !=null){
//          student.setFirstName(updatedStudentDto.getFirstName());
//    }
//
//
//          student.setFirstName(updatedStudentDto.getFirstName());
//          student.setLastName(updatedStudentDto.getLastName());
//          student.setEmail(updatedStudentDto.getEmail());
//          student.setAddress(updatedStudentDto.getAddress());
//
//          Student updatedStudentObj = studentRepo.save(student);
//          return StudentMapper.maptoStudentDto(updatedStudentObj);
//    }


    @Override
//    @CacheEvict(cacheNames = "students" ,key = "#id",allEntries = true,beforeInvocation = true)
    public void deleteStudent(Long StudentId) {

        Student student = studentRepo.findById(StudentId)
                .orElseThrow(()-> new NotFound("Student not found with id: "+StudentId));
        studentRepo.delete(student);

    }
}
