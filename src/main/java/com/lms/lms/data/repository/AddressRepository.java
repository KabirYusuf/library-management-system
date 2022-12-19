package com.lms.lms.data.repository;

import com.lms.lms.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
