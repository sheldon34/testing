//package com.example.learnnn2.StudentServiceTest;
//
//import com.example.learnnn2.Dto.StudentDto;
//import com.example.learnnn2.Entity.Student;
//import com.example.learnnn2.Repo.StudentRepo;
//import com.example.learnnn2.Service.StudentImp.StudentServiceImp;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.any;
//
//import static reactor.core.publisher.Mono.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ServiceTest {
//    @Mock
//    private StudentRepo  studentRepo;
//    @InjectMocks
//    private StudentServiceImp studentServiceImp;
//    @DisplayName("Test 1: Create student ")
//    @Test
//    public void StudentService_CreateStudent_ReturnsStudentDto(){
//        Student student = Student.builder()
//                .firstName("Don")
//                .lastName("xdon")
//                .email("don@example.com")
//                .address("home")
//                .build();
//
//        StudentDto studentDto=StudentDto.builder()
//                .firstName("Don")
//                .lastName("xdon")
//                .email("don@example.com")
//                .address("home")
//                .build();
//        //when(studentRepo.save(Mockito.any(Student.class))).thenReturn(student);
//        when(studentRepo.save(any(Student.class))).thenReturn(student);
//
//
//        //act
//
//        StudentDto savedStudents=studentServiceImp.createStudent(studentDto);
//
//        //Assert
//Assertions.assertThat(savedStudents).isNotNull();
//    }
//
//
//
//
//    @Test
//    @DisplayName("test 2: service get by id ")
//    public void getbyId(){
//        Student student=Student.builder()
//                .id(1L)
//                .firstName("fffs")
//                .lastName("dsdsds")
//                .email("sdsds@sdd.ccs")
//                .address("home")
//                .build();
//
//
//    when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
//    StudentDto savetudent=studentServiceImp.getStudentById(1L);
//    Assertions.assertThat(savetudent).isNotNull();
//
//}
//
//
//@Test
//@DisplayName("StudentService_updateService")
//public void StudentService_updateService(){
//        //assign
//        Student student=Student.builder()
//
//                .firstName("HBD")
//                .lastName("DVDV")
//                .email("hbd@hbd.ccs")
//                .address("home")
//                .build();
//        StudentDto studentDto=StudentDto.builder()
//
//                .firstName("HBD")
//                .lastName("DVDV")
//                .email("hbd@hbd.ccs")
//                .address("home")
//                .build();
//        when(studentRepo.save(any(Student.class))).thenReturn(student);
//when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
//StudentDto updatedStudent=studentServiceImp.updateStudent(1L,studentDto);
////StudentDto updateStudent=studentServiceImp.updateStudent( studentDto,1,"uijij");
//Assertions.assertThat(updatedStudent).isNotNull();
//
//
//}
//
//@Test
//@DisplayName("ServiceStudentDelete")
//public void ServiceStudentDelete(){
////assign
//        Student student=Student.builder()
//                .firstName("HBD")
//                .lastName("DVDV")
//                .email("hbd@hbd.ccs")
//                .address("home")
//                .build();
//        //act
//        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
//        //StudentDto saveStudent=studentServiceImp.getStudentById(1L);
//    //assert
//
//        assertAll(()->studentServiceImp.deleteStudent(1l));
//
//}
//
//
//}
