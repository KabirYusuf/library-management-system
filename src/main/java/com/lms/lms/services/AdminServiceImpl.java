package com.lms.lms.services;

import com.lms.lms.data.models.Admin;
import com.lms.lms.data.repositories.AdminRepository;
import com.lms.lms.dtos.request.CreateAdminRequest;
import com.lms.lms.dtos.request.LoginRequest;
import com.lms.lms.dtos.request.UpdateAdminRequest;
import com.lms.lms.dtos.response.CreateAdminResponse;
import com.lms.lms.dtos.response.LoginResponse;
import com.lms.lms.dtos.response.UpdateAdminResponse;
import com.lms.lms.exceptions.AdminException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional

public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) {
        if (adminRepository.findAdminByEmail(createAdminRequest.getEmail().toLowerCase()).isPresent())throw new AdminException("Admin with " + createAdminRequest.getEmail() + " already exist");
        Admin admin = Admin.builder().
                firstName(createAdminRequest.getFirstName()).
                lastName(createAdminRequest.getLastName()).
                email(createAdminRequest.getEmail().toLowerCase()).
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
    public LoginResponse adminLogin(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        Admin foundAdmin = findAdminByEmail(loginRequest.getEmail());
        if (foundAdmin == null)throw new AdminException("Admin with email "+loginRequest.getEmail()+" doesnt exist");
        else if (!Objects.equals(loginRequest.getPassword(), foundAdmin.getPassword()))throw new AdminException("Password is incorrect");
        else loginResponse.setMessage("Login successful");
        return loginResponse;
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public String deleteAdminById(Long id) {
        Admin foundAdmin = findAdminById(id);
        adminRepository.deleteById(foundAdmin.getId());
        return "Admin deleted successfully";
    }

    @Override
    public String deleteAdminByEmail(String email) {
        adminRepository.deleteAdminByEmail(email);
        return "Admin deleted successfully";
    }

    @Override
    public void deleteAll() {
        adminRepository.deleteAll();
    }

    @Override
    public UpdateAdminResponse updateAdminByEmail(UpdateAdminRequest updateAdminRequest) {
        if (adminRepository.findAdminByEmail(updateAdminRequest.getEmail()).isEmpty())throw new AdminException("Admin does not exist");
        Admin foundAdmin = findAdminByEmail(updateAdminRequest.getEmail());
        if (updateAdminRequest.getFirstName() != null)foundAdmin.setFirstName(updateAdminRequest.getFirstName());
        if (updateAdminRequest.getLastName() != null)foundAdmin.setLastName(updateAdminRequest.getLastName());
        if (updateAdminRequest.getPhoneNumber() != null)foundAdmin.setPhoneNumber(updateAdminRequest.getPhoneNumber());
        adminRepository.save(foundAdmin);
        UpdateAdminResponse updateAdminResponse = new UpdateAdminResponse();
        updateAdminResponse.setId(foundAdmin.getId());
        return updateAdminResponse;
    }
@Override
public UpdateAdminResponse updateAdminById(UpdateAdminRequest updateAdminRequest) {
    if (adminRepository.findById(updateAdminRequest.getId()).isEmpty())throw new AdminException("Admin does not exist");
    Admin foundAdmin = findAdminById(updateAdminRequest.getId());
    if (updateAdminRequest.getFirstName() != null)foundAdmin.setFirstName(updateAdminRequest.getFirstName());
    if (updateAdminRequest.getLastName() != null)foundAdmin.setLastName(updateAdminRequest.getLastName());
    if (updateAdminRequest.getPhoneNumber() != null)foundAdmin.setPhoneNumber(updateAdminRequest.getPhoneNumber());
    Admin updatedAdmin = adminRepository.save(foundAdmin);
    UpdateAdminResponse updateAdminResponse = new UpdateAdminResponse();
    return mapper.map(updatedAdmin, UpdateAdminResponse.class);
}



}
