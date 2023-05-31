package com.br.icns.api.controllers;
import com.br.icns.api.dtos.EmpresasDTO;
import com.br.icns.api.dtos.NotasDTO;
import com.br.icns.api.dtos.ProdutosDTO;
import com.br.icns.api.models.Empresas;
import com.br.icns.api.models.Notas;
import com.br.icns.api.models.Produtos;
import com.br.icns.api.models.User;
import com.br.icns.api.services.EmpresasService;
import com.br.icns.api.services.ProdutosService;
import com.br.icns.api.services.nota.NotasService;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/user/notas")
public class NotasController {
    @Autowired
    private NotasService notasService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmpresasService empresasService;

    @Autowired
    private    ProdutosService produtosService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public ResponseEntity<Object> getNota(){
        return ResponseEntity.ok().body(notasService.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findNotaByIdNotas(@PathVariable String id){
        try{
            Optional<Notas> nota = getNotas(id);
            ResponseEntity<Object> NOT_FOUND = getObjectResponseEntity(nota);
            if (NOT_FOUND != null) return NOT_FOUND;
            return ResponseEntity.ok().body(nota);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Conflict\":\""+e.getMessage()+"\"}");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<Object> saveNota( @RequestBody NotasDTO notasDTO, HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        User user = getUserByToken(bearerToken);
        Optional<Empresas> empresas = Optional.ofNullable(empresasService.findEmpresasByCnpj(notasDTO.empresa().cnpj()));

        if(notasService.existsNotaByKeyNota(notasDTO.keyNota())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"Conflict\":\"A Chave da nota, já está em uso! \"}");
        }
        if(empresas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Cod. da empresa não encontrada \"}");
        }
        if(notasDTO.produtos().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Prod. vazios! \"}");
        }
        List<Produtos> produtosList = new ArrayList<>();

        for (ProdutosDTO produtosDTO : notasDTO.produtos()) {
            Produtos produtos = new Produtos(produtosDTO);
            produtosList.add(produtosService.save(produtos));
        }

        Notas nota = new Notas(
                notasDTO.totalValue(),
                notasDTO.keyNota(),
                produtosList,
                user,
                empresas.get(),
                LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(notasService.save(nota));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNota(@PathVariable String id) {
        try {
            Optional<Notas> nota = getNotas(id);
            ResponseEntity<Object> NOT_FOUND = getObjectResponseEntity(nota);
            if (NOT_FOUND != null) return NOT_FOUND;
            notasService.delete(nota.get());
            return ResponseEntity.ok().body("{\"message\":\"ok!\"}");

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Conflict\":\""+e.getMessage()+"\"}");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateNota(@PathVariable String id, @RequestBody @Valid NotasDTO notasDTO) {
        try {
            Optional<Notas> nota = getNotas(id);
            ResponseEntity<Object> NOT_FOUND = getObjectResponseEntity(nota);
            if (NOT_FOUND != null) return NOT_FOUND;
            Notas saveNewNota = nota.get();
            saveNewNota.setDataLastUpdate(LocalDateTime.now(ZoneId.of("UTC")));
            saveNewNota.setKeyNota(notasDTO.keyNota());
            saveNewNota.setTotalValue(notasDTO.totalValue());
            saveNewNota= notasService.save(saveNewNota);
            return ResponseEntity.ok().body(saveNewNota);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Conflict\":\""+e.getMessage()+"\"}");
        }
    }

    private static ResponseEntity<Object> getObjectResponseEntity(Optional<Notas> nota) {
        if(nota.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Não encontrado \"}");
        }
        return null;
    }

    private Optional<Notas> getNotas(String id) {
        id=id.strip().replace(" ","");
        Optional<Notas> nota = Optional.ofNullable(notasService.findNotasByKeyNota(id));
        return nota;
    }


    private User getUserByToken(String bearerToken) {
        var subject = tokenService.getSubject(bearerToken);
        return userService.findByUsername(subject);
    }

}
