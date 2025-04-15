package com.example.learnnn2.StudentServiceTest;

import com.example.learnnn2.Dto.StudentDto;
import com.example.learnnn2.Entity.Student;
import com.example.learnnn2.Repo.StudentRepo;
import com.example.learnnn2.Service.StudentImp.StudentServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class ServiceLayer2 {
    @Mock
private StudentRepo studentRepo;
    @InjectMocks
    @Spy
    private StudentServiceImp studentServiceImp;
@InjectMocks
private Student student ;
@InjectMocks
private StudentDto studentDto;

@BeforeEach
    public void init(){
   Student  student = Student.builder()
            .id(1l)
            .address("address")
            .email("email")
            .firstName("firstName")
            .lastName("lastName")
            .build();
    StudentDto studentDto=StudentDto.builder()
         .id(1l)

        .address("address")
        .email("email")
        .firstName("firstName")
        .lastName("lastName")

        .build();

}
@Test
@Order(1)
public void StudentService_CreateStudent_ReturnStudentDto() {

    /// Assign
// mocking repo to return entity not DTO
   when(studentRepo.save(any(Student.class))).thenReturn(student);

   /// Act
   //Testing service method that should convert entity to DTO
   StudentDto savedStudentDto = studentServiceImp.createStudent(studentDto);

/// Assert

//assertThat(savedStudentDto).isEqualTo(student);
assertThat(savedStudentDto).isNotNull();
}
@Test
@Order(2)
    public void StudentService_GetStudentById_ReturnStudentDto() {
    //assign

    when(studentRepo.findById(student.getId())).thenReturn(Optional.of(student));
    //act

    StudentDto savedStudentDto= studentServiceImp.getStudentById(student.getId());

    //assert
    assertThat(savedStudentDto).isNotNull();
}


@Test
@Order(3)
    public void StudentService_GetAllStudents_ReturnStudentDto() {
    //assign
    when(studentRepo.findAll()).thenReturn(List.of(student));
    //act
    List<StudentDto> students=studentServiceImp.getAllStudents();
    //assert
    assertThat(students).isNotNull();
    assertThat(students.size()).isEqualTo(1);

}
@Test

    public void StudentService_UpdateStudent_ReturnUpdatedStudentDto() {

    //assert
    when(studentRepo.save(any(Student.class))).thenReturn(student);
    when(studentRepo.findById(1l)).thenReturn(Optional.of(student));
    //act
    StudentDto UpdatedStudent=studentServiceImp.updateStudent(1l,studentDto);
   //assert
    assertThat(UpdatedStudent).isNotNull();

}
@Test
@Order(4)
    public void StudentService_DeleteStudent_ReturnDeletedStudentDto() {
    when(studentRepo.findById(1l)).thenReturn(Optional.of(student));

    assertAll(()->studentRepo.deleteById(1l));
    assertAll(()->studentServiceImp.deleteStudent(1l));

}
}