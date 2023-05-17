package com.br.icns.api.controllers;

import com.br.icns.api.dtos.ProdutosDTO;
import com.br.icns.api.services.ProdutosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;


    @GetMapping
    public ResponseEntity<Object> getProdutos(){
        return ResponseEntity.ok().body(produtosService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProdutosById(){
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping
    public ResponseEntity<Object> saveProdutos(@RequestBody @Valid ProdutosDTO produtosDTO){
        return ResponseEntity.ok().body(produtosDTO);
    }
}
