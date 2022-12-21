package com.lms.lms.controllers;

import com.lms.lms.data.models.Admin;
import com.lms.lms.dtos.request.CreateAdminRequest;
import com.lms.lms.dtos.request.LoginRequest;
import com.lms.lms.dtos.response.CreateAdminResponse;
import com.lms.lms.dtos.response.LoginResponse;
import com.lms.lms.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<CreateAdminResponse> createAdmin(@RequestBody CreateAdminRequest createAdminRequest){
        return new ResponseEntity<>(adminService.createAdmin(createAdminRequest), HttpStatus.CREATED);

    }

    @GetMapping("/findById/{adminId}")
    public ResponseEntity<Admin> findAdminById(@PathVariable("adminId") Long adminId){
        return new ResponseEntity<>(adminService.findAdminById(adminId), HttpStatus.CREATED);
    }
    @GetMapping("/findByEmail/{adminEmail}")
    public ResponseEntity<Admin> findAdminByEmail(@PathVariable("adminEmail") String adminEmail){
        return new ResponseEntity<>(adminService.findAdminByEmail(adminEmail), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return adminService.adminLogin(loginRequest);
    }

    @GetMapping("/getAdmins")
    public ResponseEntity<List<Admin>> getAdmins(){
        return new ResponseEntity<>(adminService.getAdmins(), HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteById/{adminId}")
    public String deleteAdminById(@PathVariable("adminId") Long id){
//        return new  ResponseEntity<>(adminService.deleteAdminById(id), HttpStatus.CREATED);
        return adminService.deleteAdminById(id);

    }

    @DeleteMapping("/deleteByEmail/{adminEmail}")
    public String deleteAdminByEmail(@PathVariable("adminEmail") String email){
//        return new  ResponseEntity<>(adminService.deleteAdminById(id), HttpStatus.CREATED);
        return adminService.deleteByEmail(email);

    }

}
