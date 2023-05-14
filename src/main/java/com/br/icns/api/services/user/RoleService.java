package com.br.icns.api.services.user;

import com.br.icns.api.enums.RoleName;
import com.br.icns.api.models.Role;
import com.br.icns.api.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
