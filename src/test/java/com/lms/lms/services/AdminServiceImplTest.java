package com.lms.lms.services;

import com.lms.lms.data.models.Admin;
import com.lms.lms.dtos.request.CreateAdminRequest;
import com.lms.lms.dtos.request.LoginRequest;
import com.lms.lms.dtos.response.CreateAdminResponse;
import com.lms.lms.dtos.response.LoginResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

//    @MockBean
//    private AdminRepository adminRepository;
    private CreateAdminRequest createAdminRequest;

    private  Admin admin;

    private CreateAdminResponse createAdminResponse;


    @BeforeEach
    void setUp() {
        createAdminRequest = new CreateAdminRequest();;
        createAdminRequest.setEmail("bolaji@gmail.com");
        createAdminRequest.setFirstName("Kabir");
        createAdminRequest.setLastName("Yusuf");
        createAdminRequest.setPassword("ade1236");
        createAdminRequest.setPhoneNumber("08065923833");


    }

    @AfterEach
    void afterEach(){
        adminService.deleteAll();
    }
    @Test
    void testThatAdminCanBeCreated(){
        createAdminResponse = adminService.createAdmin(createAdminRequest);
        assertNotNull(createAdminResponse.getId());
        assertEquals("bolaji@gmail.com", createAdminResponse.getEmail());
    }
    @Test
    void testThatAdminCanBeFoundByEmail(){
        createAdminResponse = adminService.createAdmin(createAdminRequest);
        Admin foundAdmin = adminService.findAdminByEmail("bolaji@gmail.com");
        assertEquals(createAdminResponse.getEmail(), foundAdmin.getEmail());
    }
    @Test
    void testThatAdminCanLoginUsingTheCorrectEmailAndPassWord(){
        createAdminResponse = adminService.createAdmin(createAdminRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("ade1236");
        loginRequest.setEmail("bolaji@gmail.com");
        LoginResponse loginResponse = adminService.adminLogin(loginRequest);
        assertEquals("Login successful", loginResponse.getMessage());
    }
    @Test
    void testThatListOfAdminIsGreaterIncreasesByOneWhenANewAdminIsCreatedAndSaved(){
        adminService.deleteAll();
        createAdminResponse = adminService.createAdmin(createAdminRequest);
        List<Admin> adminsAfterSavingFirstAdmin = adminService.getAdmins();
        assertEquals(1, adminsAfterSavingFirstAdmin.size());
        CreateAdminRequest createAdminRequest1 = new CreateAdminRequest();
        createAdminRequest1.setEmail("jaybee@gmail.com");
        createAdminRequest1.setFirstName("Kabir");
        createAdminRequest1.setLastName("Yusuf");
        createAdminRequest1.setPassword("ade1236");
        createAdminRequest1.setPhoneNumber("08065923833");

        adminService.createAdmin(createAdminRequest1);

        List<Admin> adminsAfterSavingSecondAdmin = adminService.getAdmins();
        assertEquals(2, adminsAfterSavingSecondAdmin.size());
    }
    @Test
    void testThatSizeOfAdminListDecreaseByOneWhenOneAdminIsDeleted(){
        createAdminResponse = adminService.createAdmin(createAdminRequest);
        List<Admin> listOfAdminsAfterSavingOneAdmin = adminService.getAdmins();
        assertEquals(1, listOfAdminsAfterSavingOneAdmin.size());

        adminService.deleteByEmail("bolaji@gmail.com");

        List<Admin> listOfAdminsAfterDeletingOneAdmin = adminService.getAdmins();
        assertEquals(0, listOfAdminsAfterDeletingOneAdmin.size());

    }
}