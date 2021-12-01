package com.exist.core.repository;

import com.exist.core.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role deleteRoleByRoleId(long roleId);
}
