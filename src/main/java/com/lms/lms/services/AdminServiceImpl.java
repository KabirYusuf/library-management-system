package com.lms.lms.services;

import com.lms.lms.data.models.Admin;
import com.lms.lms.data.repository.AdminRepository;
import com.lms.lms.dtos.request.CreateAdminRequest;
import com.lms.lms.dtos.request.LoginRequest;
import com.lms.lms.dtos.response.CreateAdminResponse;
import com.lms.lms.dtos.response.LoginResponse;
import com.lms.lms.exceptions.AdminException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) {
        if (adminRepository.findAdminByEmail(createAdminRequest.getEmail()).isPresent())throw new AdminException("Admin with " + createAdminRequest.getEmail() + " already exist");
        Admin admin = Admin.builder().
                firstName(createAdminRequest.getFirstName()).
                lastName(createAdminRequest.getLastName()).
                email(createAdminRequest.getEmail()).
                password(createAdminRequest.getPassword()).
                phoneNumber(createAdminRequest.getPhoneNumber()).build();
        Admin savedAdmin = adminRepository.save(admin);
        return mapper.map(savedAdmin, CreateAdminResponse.class);
    }

    @Override
    public Admin findAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(()-> new AdminException("Admin with id "+id+ " does not exist" ));
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email).orElseThrow(()-> new AdminException("Admin with email "+email+ " does not exist" ));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        Admin foundAdmin = findAdminByEmail(loginRequest.getEmail());
        if (foundAdmin == null)throw new AdminException("Admin with email "+loginRequest.getEmail()+" doesnt exist");
        else if (!Objects.equals(loginRequest.getPassword(), foundAdmin.getPassword()))throw new AdminException("Password is incorrect");
        else loginResponse.setMessage("Login successful");
        return loginResponse;
//        if (Objects.equals(loginRequest.getPassword(), foundAdmin.getPassword()))loginResponse.setMessage("Login successful");
//        else loginResponse.setMessage("Password is incorrect");
//        return loginResponse;
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
}
