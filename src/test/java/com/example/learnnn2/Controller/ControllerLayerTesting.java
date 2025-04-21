package com.example.learnnn2.Controller;


import com.example.learnnn2.Dto.StudentDto;
import com.example.learnnn2.Entity.Student;
import com.example.learnnn2.Repo.StudentRepo;
import com.example.learnnn2.Service.StudentImp.StudentServiceImp;
import com.example.learnnn2.Service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.h2.index.IndexCondition.get;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.awaitility.Awaitility.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
response.andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("xdon"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("don"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("cdon 12 bird"))
        .andDo(MockMvcResultHandlers.print());
}

@Test
@Order(2)
    public  void StudentController_GetAllStudent_ReturnResponseDto() throws Exception {

        List<StudentDto> students=new ArrayList<>();
        students.add(studentDto);
        students.add(StudentDto.builder()
                        .id(2l)
                        .address("cdon 13 bird")
                        .lastName("xdommn")
                        .firstName("xdonu")
                        .email("test@testgfgnfn.com")
                .build());
        when(studentService.getAllStudents()).thenReturn(students);

        ResultActions response =mockMvc.perform(get("/api/student/getall")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(students))
        );
        // Checking on the status of the get request
        response.andExpect(status().isOk())
                // Outputing the list that we are testing on
                .andDo(MockMvcResultHandlers.print())
                // comparing the 2 output size of the list that have been obtained using the get method
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));

        ;}
    @Test
@Order(3)
public void StudentController_GetStudentById_ReturnResponseDto() throws Exception {
        Long studentId=student.getId();//or you can actually pass a the real id here

        when(studentService.getStudentById(studentId)).thenReturn(studentDto);
        ResultActions response=mockMvc.perform(get("/api/student/"+studentId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(studentDto)));

        response.andExpect(status().isOk())
               .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("xdon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("don"))
        ;


    }
    @Test
    @Order(4)
    public void StudentController_UpdateStudentDto_ReturnUpdatedStudentDto() throws Exception {

        Long studentId=student.getId();

when(studentService.updateStudent(studentId,studentDto)).thenReturn(studentDto);

ResultActions response=mockMvc.perform(patch("/api/student/update/"+studentId)
        .content(objectMapper.writeValueAsString(studentDto))
.contentType(MediaType.APPLICATION_JSON));

response.andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("xdon"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("don"));




    }
@Test
    public void StudentController_DeleteStudent_ReturnDeletedStudentDto() throws Exception {
        Long studentId=student.getId();

    doNothing().when(studentService).deleteStudent(studentId);
        ResultActions response=mockMvc.perform(delete("/api/student/delete/"+studentId)

        .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(status().isOk());
    }

}
