package com.br.icns.api.controllers.user;

import com.br.icns.api.dtos.LoginDTO;
import com.br.icns.api.models.User;
import com.br.icns.api.services.user.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/login")
@Controller
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO login){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body("{\"token\":\""+tokenService.gerarToken(user)+"\"}");
    }

}
