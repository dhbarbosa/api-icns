package com.br.icns.api.repositories;

import com.br.icns.api.enums.RoleName;
import com.br.icns.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByRoleName(RoleName roleName);
}
