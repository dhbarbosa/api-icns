package com.br.icns.api.controllers.nota;
import com.br.icns.api.dtos.NotasDTO;
import com.br.icns.api.models.Notas;
import com.br.icns.api.models.User;
import com.br.icns.api.services.nota.NotaService;
import com.br.icns.api.services.user.TokenService;
import com.br.icns.api.services.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("api/user/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public ResponseEntity<Object> getNota(){
        return ResponseEntity.ok().body(notaService.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findNotaByIdNotas(@PathVariable String id){
        try{
            UUID uuid = UUID.fromString(id);
            Optional<Notas> nota = Optional.ofNullable(notaService.findNotaByIdNotas(uuid));
            if(nota.isEmpty()){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Não encontrado \"}");
            }
            return ResponseEntity.ok().body(nota);

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Conflict\":\""+e.getMessage()+"\"}");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<Object> saveNota( @Valid @RequestBody NotasDTO notasDTO, HttpServletRequest request){
        if(notaService.existsNotaByKeyNota(notasDTO.keyNota())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"Conflict\":\"A Chave da nota, já está em uso! \"}");

        }
        String bearerToken = request.getHeader("Authorization");
        User user = getUserByToken(bearerToken);
        Notas nota = new Notas(
                notasDTO.totalValue(),
                notasDTO.keyNota(),
                user,
                LocalDateTime.now(ZoneId.of("UTC")));


        return ResponseEntity.ok().body(notaService.save(nota));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNota(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Optional<Notas> nota = Optional.ofNullable(notaService.findNotaByIdNotas(uuid));
            if(nota.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Não encontrado \"}");
            }
            notaService.delete(nota.get());
            return ResponseEntity.ok().body("{\"message\":\"ok!\"}");

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Conflict\":\""+e.getMessage()+"\"}");
        }
    }



    private User getUserByToken(String bearerToken) {
        var subject = tokenService.getSubject(bearerToken);
        return userService.findByUsername(subject);
    }

}
