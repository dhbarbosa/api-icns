package com.br.icns.api.controllers;

import com.br.icns.api.dtos.UserDTO;
import com.br.icns.api.enums.RoleName;
import com.br.icns.api.models.Role;
import com.br.icns.api.models.User;
import com.br.icns.api.services.user.RoleService;
import com.br.icns.api.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Controller
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/cadastro")
    public ResponseEntity<Object> creatNewUser(@RequestBody @Valid UserDTO userDTO){
        if(userService.existsUserByUsername(userDTO.username())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"Conflict\":\"O CPF já está em uso!\"}");
        }
        userDTO = userDTO.setPassword(passwordEncoder.encode(userDTO.password()));
        Set<Role> roles = Collections.singleton(roleService.findByRoleName(RoleName.ROLE_USER));
        var user = new User(
                userDTO,
                LocalDateTime.now(ZoneId.of("UTC")),
                roles);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public  ResponseEntity<Object> findUser(@PathVariable(name = "id") String cpf){
        Optional<User> user = Optional.ofNullable(userService.findByUsername(cpf));
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Não encontrado \"}");
        }
        return ResponseEntity.ok().body(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public  ResponseEntity<Object> updateUser(@PathVariable(name = "id") String cpf,@RequestBody @Valid UserDTO userDTO){
        Optional<User> user = Optional.ofNullable(userService.findByUsername(cpf));
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Não encontrado \"}");
        }
        User userUpdate = user.get();
        userUpdate.setDataLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
        userUpdate.setName(userDTO.name());
        userUpdate.setUsername(userDTO.username());
        userUpdate.setPassword(passwordEncoder.encode(userDTO.password()));
        userUpdate.setRoles(user.get().getRoles());
        userService.save(userUpdate);
        return ResponseEntity.ok().body(userUpdate);
    }
}
