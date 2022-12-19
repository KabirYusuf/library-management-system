package com.lms.lms.services;

import com.lms.lms.data.models.Admin;
import com.lms.lms.data.repository.AdminRepository;
import com.lms.lms.dtos.request.CreateAdminRequest;
import com.lms.lms.dtos.response.CreateAdminResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private AdminRepository adminRepository;
    private CreateAdminRequest createAdminRequest;


    @BeforeEach
    void setUp() {
        createAdminRequest = new CreateAdminRequest();
        createAdminRequest.setEmail("kabir@gmail.com");
        createAdminRequest.setFirstName("Kabir");
        createAdminRequest.setLastName("Yusuf");
        createAdminRequest.setPassword("ade1234");
        createAdminRequest.setPhoneNumber("08065923833");
        Admin admin = new Admin();
        admin.setEmail(createAdminRequest.getEmail());
        admin.setFirstName(createAdminRequest.getFirstName());
        admin.setLastName(createAdminRequest.getPassword());
        admin.setPhoneNumber(createAdminRequest.getPhoneNumber());
        Mockito.when(adminRepository.save(admin)).thenReturn(admin);
    }
    @Test
    void testThatAdminCanBeCreated(){

        CreateAdminResponse createAdminResponse = adminService.createAdmin(createAdminRequest);
        assertEquals("kabir@gmail.com", createAdminResponse.getEmail());
    }
}