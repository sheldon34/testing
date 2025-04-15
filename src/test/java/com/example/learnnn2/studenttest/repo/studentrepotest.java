package com.example.learnnn2.studenttest.repo;

import com.example.learnnn2.Entity.Student;
import com.example.learnnn2.Repo.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest

public class studentrepotest {
    @Autowired
    private StudentRepo studentRepo;
    /// function naming structure
    // public void - tests dont return any thing
    // intial function_what to be done _ results as below
    @Test
    public void StudentRepo_SaveAll_ReturnSavedStudents(){


        //Arrange
        Student student=Student.builder()
                .email("studen@gmail.com")
                .firstName("xdon")
                .address("homr")
                .lastName("don")
                .build();

        //Act

Student SavedStudent1=studentRepo.save(student);
        //Assert
Assertions.assertNotNull(SavedStudent1);
assertThat(SavedStudent1.getId()).isGreaterThan(0);
       // Assertions.assert(SavedStudent1.getId())
    };



    @Test
    public void StudentRepo_GetAll_ReturnAllStudents(){
        /// arrange
        Student student=Student.builder()
                .email("student@email.com")
                .firstName("shel")
                .lastName("don")
                .address("shome")
                .build();

        Student Student2=Student.builder()
                .email("memail@student.com")
                .firstName("mukam")
                .lastName("kabom")
                .address("kabomhome")
                .build();


        Student SavedStudent=studentRepo.save(student);
        Student SavedStudent2=studentRepo.save(Student2);


        /// Act
        List<Student> findAllStudents=studentRepo.findAll();


        /// Assert
       // assertThat(findAllStudents.size()).isEqualTo(2);
     assertThat(findAllStudents).isNotNull();
       assertThat(findAllStudents.size()).isEqualTo(2);
       // Assertions.assertNotNull(findAllStudents);
    }



    @Test

    public void StudentRepo_GetById_ReturnStudent(){
        /// arrange
        Student student=Student.builder()
                .email("memail@student.com")
                .firstName("mukam")
                .lastName("kabom")
                .address("ka")
                .build();

Student SavedStudent=studentRepo.save(student);
///Act

Student findByidStudent=studentRepo.findById(SavedStudent.getId()).get();

/// Assert
        assertThat(findByidStudent).isNotNull();


                }

    @Test
    public void StudentRepo_FindByEmail_ReturnStudentByEmail(){
        //Assign
        Student student=Student.builder()
                .email("memail@student.com")
                .firstName("mukam")
                .lastName("kabom")
                .address("ka")
                .build();
        Student SavedStudent=studentRepo.save(student);
        //act
        Student findbyEmail=studentRepo.findByEmail(student.getEmail()).get();
        //Assert
        assertThat(findbyEmail).isNotNull();
        assertThat(findbyEmail.getEmail()).isEqualTo("memail@student.com");
    }
@Test
    public void StudentRepo_Update_ReturnUpdatedStudent(){
//Assign
        Student student=Student.builder()
                .email("memail@student.com")
                .firstName("mukam")
                .lastName("kabom")
                .address("ka")
                .build();
        Student SavedStudent=studentRepo.save(student);
        Student updatedStudent=studentRepo.findById(SavedStudent.getId()).get();
        updatedStudent.setFirstName("xdon");
        updatedStudent.setAddress("home");
        updatedStudent.setLastName("don");


        //Act
        Student updatedStudentObj= studentRepo.save(updatedStudent);

//Assert
        assertThat(updatedStudentObj).isNotNull();
        assertThat(updatedStudentObj.getAddress()).isEqualTo("home");
        assertThat(updatedStudentObj.getLastName()).isEqualTo("don");
        assertThat(updatedStudentObj.getFirstName()).isEqualTo("xdon");

    }
    @Test
    public void StudentRepo_DeleteById_ReturnStudent(){
        Student student=Student.builder()
                .email("memail@student.com")
                .firstName("mukam")
                .lastName("kabom")
                .address("ka")
                .build();
        Student SavedStudent=studentRepo.save(student);

        //act
        studentRepo.deleteById(SavedStudent.getId());
        Optional<Student> findbyidStudent=studentRepo.findById(SavedStudent.getId());

        //Assert
        assertThat(findbyidStudent).isEmpty();
    }

}
