package com.lms.lms.services;

import com.lms.lms.data.models.Admin;
import com.lms.lms.data.repository.AdminRepository;
import com.lms.lms.dtos.request.CreateAdminRequest;
import com.lms.lms.dtos.response.CreateAdminResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) {
        Admin admin = Admin.builder().
                firstName(createAdminRequest.getFirstName()).
                lastName(createAdminRequest.getLastName()).
                email(createAdminRequest.getEmail()).
                password(createAdminRequest.getPassword()).
                phoneNumber(createAdminRequest.getPhoneNumber()).build();
        Admin savedAdmin = adminRepository.save(admin);
        System.out.println(savedAdmin);
        return mapper.map(savedAdmin, CreateAdminResponse.class);

    }

    @Override
    public Admin findAdminById(int id) {
        return null;
    }
}
