package com.example.learnnn2.Controller;


import com.example.learnnn2.Dto.StudentDto;
import com.example.learnnn2.Entity.Student;
import com.example.learnnn2.Repo.StudentRepo;
import com.example.learnnn2.Service.StudentImp.StudentServiceImp;
import com.example.learnnn2.Service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.awaitility.Awaitility.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)

public class ControllerLayerTesting {
    @Autowired
  private  MockMvc mockMvc;
    @Mock
    private StudentRepo studentrepo;
    @MockitoBean
    private StudentServiceImp studentService;

    @Autowired
    private ObjectMapper objectMapper;


private StudentRepo studentRepo;

    private Student student;

    private StudentDto studentDto;

    @BeforeEach
    public void setup() {
         student = Student.builder()
                .id(1l)
                .email("test@test.com")
                .firstName("xdon")
                .lastName("don")
                .address("cdon 12 bird")
                .build();

    studentDto =studentDto.builder()
            .id(1l)
            .email("test@test.com")
            .firstName("xdon")
            .lastName("don")
            .address("cdon 12 bird")
            .build();

    }







@Test
    public void StudentController_CreateStudentDto_ReturnCreatedStudentDto() throws Exception {
        given(studentService.createStudent(ArgumentMatchers.any())).willAnswer(invocations->invocations.getArgument(0));

    ResultActions response=mockMvc.perform(post("/api/student/upload")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(studentDto)));
response.andExpect(MockMvcResultMatchers.status().isCreated()
);





}
}
