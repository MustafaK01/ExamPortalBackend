package com.examportal.examportalbackend.repository;

import com.examportal.examportalbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
