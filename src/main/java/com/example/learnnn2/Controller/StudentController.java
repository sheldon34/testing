package com.example.learnnn2.Controller;

import com.example.learnnn2.Dto.StudentDto;
import com.example.learnnn2.Service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ErrorHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/student")
@Validated

@Controller

public class StudentController   {
    @Autowired
    private StudentService studentService;
    /// creating new class
    ///
@PostMapping("/upload")

    ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        StudentDto savedStudent=studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);

    }
    @GetMapping("/{id}")

     ResponseEntity<StudentDto> getStudentById(@RequestBody @PathVariable("id") Long StudentId){
        StudentDto studentDto=studentService.getStudentById(StudentId);
        return ResponseEntity.ok(studentDto);
    }
    @GetMapping("/getall")


     ResponseEntity<List<StudentDto>> getaAllStudent(){

        List<StudentDto> students=studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    @PatchMapping("/update/{id}")

    public ResponseEntity<StudentDto> updateStudent (@RequestBody @PathVariable("id")
                                                         @Valid Long StudentId, StudentDto updatedStudentDto){
        StudentDto studentDto=studentService.updateStudent(StudentId,updatedStudentDto);
        return ResponseEntity.ok(studentDto);
    }
    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
