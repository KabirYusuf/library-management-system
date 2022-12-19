package com.lms.lms.services;

import com.lms.lms.data.models.Admin;
import com.lms.lms.dtos.request.CreateAdminRequest;
import com.lms.lms.dtos.response.CreateAdminResponse;

public interface AdminService {
    CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest);
    Admin findAdminById(int id);
}
