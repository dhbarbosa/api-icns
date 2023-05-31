package com.br.icns.api.config.component;

import com.br.icns.api.dtos.UserDTO;
import com.br.icns.api.enums.RoleName;
import com.br.icns.api.models.Role;
import com.br.icns.api.models.User;
import com.br.icns.api.services.user.RoleService;
import com.br.icns.api.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class InitApp implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${api.usernameAdmin}")
    private String username;

    @Value("${api.passwordAdmin}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roles = roleService.findAll();
        if(roles.isEmpty()){
            roleService.save(new Role(RoleName.ROLE_USER));
            roleService.save(new Role(RoleName.ROLE_ADMIN));
        }
        Optional<User> user= Optional.ofNullable(userService.findByUsername(username));

        if(user.isEmpty()){
            Set<Role> userAdmin = Collections.singleton(roleService.findByRoleName(RoleName.ROLE_ADMIN));

            userService.save(new User(
                    new UserDTO(username,"null", passwordEncoder.encode(password)),
                    LocalDateTime.now(ZoneId.of("UTC")),
                    userAdmin
            ));
        }
    }
}
