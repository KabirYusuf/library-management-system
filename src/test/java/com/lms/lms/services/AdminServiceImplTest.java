package com.lms.lms.services;

import com.lms.lms.data.models.Admin;
import com.lms.lms.data.repository.AdminRepository;
import com.lms.lms.dtos.request.CreateAdminRequest;
import com.lms.lms.dtos.request.LoginRequest;
import com.lms.lms.dtos.response.CreateAdminResponse;
import com.lms.lms.dtos.response.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private AdminRepository adminRepository;
    private CreateAdminRequest createAdminRequest;
    Admin admin = new Admin();


    @BeforeEach
    void setUp() {
        createAdminRequest = new CreateAdminRequest();
        createAdminRequest.setEmail("kabir@gmail.com");
        createAdminRequest.setFirstName("Kabir");
        createAdminRequest.setLastName("Yusuf");
        createAdminRequest.setPassword("ade1234");
        createAdminRequest.setPhoneNumber("08065923833");

        admin.setEmail(createAdminRequest.getEmail());
        admin.setFirstName(createAdminRequest.getFirstName());
        admin.setLastName(createAdminRequest.getPassword());
        admin.setPhoneNumber(createAdminRequest.getPhoneNumber());
        Mockito.when(adminRepository.save(admin)).thenReturn(admin);
        Mockito.when(adminRepository.findAdminByEmail("kabir@gmail.com")).thenReturn(Optional.ofNullable(admin));
    }
    @Test
    void testThatAdminCanBeCreated(){

        CreateAdminResponse createAdminResponse = adminService.createAdmin(createAdminRequest);
        assertEquals("kabir@gmail.com", createAdminResponse.getEmail());
    }
    @Test
    void testThatAdminCanBeFoundByEmail(){
//        CreateAdminResponse createAdminResponse = adminService.createAdmin(createAdminRequest);
        Admin foundAdmin = adminService.findAdminByEmail("kabir@gmail.com");
        assertEquals(admin, foundAdmin);
    }
    @Test
    void testThatAdminCanLoginUsingTheCorrectEmailAndPassWord(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("kabir@gmail.com");
        loginRequest.setPassword("ade1234");
        System.out.println(admin.getPassword());
        System.out.println(admin.getEmail());
        System.out.println(admin.getPassword());
        System.out.println(admin.getFirstName());
        LoginResponse loginResponse = adminService.login(loginRequest);
        assertEquals("Login successful", loginResponse.getMessage());
    }
}