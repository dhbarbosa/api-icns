package com.br.icns.api.controllers;
import com.br.icns.api.dtos.ProdutosDTO;
import com.br.icns.api.models.Notas;
import com.br.icns.api.models.Produtos;
import com.br.icns.api.services.ProdutosService;
import com.br.icns.api.services.nota.NotasService;
import jakarta.validation.Valid;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/user/produtos")
public class ProdutosController {

    @Autowired
    private NotasService notasService;

    @Autowired
    private ProdutosService produtosService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public ResponseEntity<Object> getProdutos() {
        return ResponseEntity.ok().body(produtosService.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProdutosByCodProduto(@PathVariable String id) {
        Optional<List<Produtos>> produto = Optional.ofNullable(produtosService.findAllProdutosByCodProduto(id));
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Não encontrado \"}");
        }
        return ResponseEntity.ok().body(produto.get());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<Object> saveProdutos(@Valid @RequestBody ProdutosDTO produtosDTO) throws IOException {
        Optional<Notas> notaExiste = Optional.ofNullable(notasService.findNotasByKeyNota(produtosDTO.notasDTO().keyNota()));
            if (notaExiste.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Cod. da nota não encontrado \"}");
            }
            var newProduto = new Produtos(produtosDTO);
            newProduto.setNotas(notaExiste.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(produtosService.save(newProduto));
        }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> deleteProdutos(@PathVariable String uuid){
        try {
            Optional<Produtos> produtos = Optional.ofNullable(produtosService.findProdutosByIdProdutos(UUID.fromString(uuid)));
            if(produtos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Conflict\":\"Não encontrado o Produto com essee UUID. \"}");
            }
            produtosService.delete(produtos.get());
            return ResponseEntity.ok().body("{\"Mensage\":\"ok\"}");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Erro\":\"Id invalido.\"}");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{codProduto}")
    public ResponseEntity<Object> updateProdutos(@PathVariable String codProduto){
        return ResponseEntity.ok().body("ok");

    }

}