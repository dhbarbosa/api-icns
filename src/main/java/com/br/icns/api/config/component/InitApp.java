package com.br.icns.api.config.component;

import com.br.icns.api.enums.RoleName;
import com.br.icns.api.models.Role;
import com.br.icns.api.services.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class InitApp implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = roleService.findAll();
        if(roles.isEmpty()){
            roleService.save(new Role(RoleName.ROLE_USER));
            roleService.save(new Role(RoleName.ROLE_ADMIN));
        }
    }
}
