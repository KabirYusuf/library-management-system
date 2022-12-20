package com.lms.lms.services;

import com.lms.lms.data.models.Admin;
import com.lms.lms.dtos.request.CreateAdminRequest;
import com.lms.lms.dtos.request.LoginRequest;
import com.lms.lms.dtos.response.CreateAdminResponse;
import com.lms.lms.dtos.response.LoginResponse;

import java.util.List;

public interface AdminService {
    CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest);
    Admin findAdminById(Long id);

    Admin findAdminByEmail(String email);

    LoginResponse login(LoginRequest loginRequest);

    List<Admin> getAdmins();
}
