package com.br.icns.api.controllers;
import com.br.icns.api.dtos.EmpresasDTO;
import com.br.icns.api.models.Empresas;
import com.br.icns.api.services.EmpresasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("api/user/empresas")
class EmpresasController {
    @Autowired
    private EmpresasService empresasService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public ResponseEntity<Object> getEmpresas() {
        return ResponseEntity.ok().body(empresasService.findAll());
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{cnpj}")
    public ResponseEntity<Object> getEmpresasByCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok().body(empresasService.findEmpresasByCnpj(cnpj));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<Object> saveEmpresas(@RequestBody @Valid EmpresasDTO empresasDTO) {
        if(empresasService.existsEmpresasByCpnj(empresasDTO.cnpj())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\":\"Empresa já esta cadastrada\"}");
        }
        System.out.println(empresasDTO);
        Empresas empresa = new Empresas(empresasDTO.cnpj(),empresasDTO.nomeFantasia(),empresasDTO.email(),empresasDTO.contato());
        return ResponseEntity.status(HttpStatus.CREATED).body(empresasService.save(empresa));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{cnpj}")
    public  ResponseEntity<Object> deleteEmpresa(@PathVariable String cnpj){
        Optional<Empresas> empresa = Optional.ofNullable(empresasService.findEmpresasByCnpj(cnpj));
        if(empresa.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\":\"Empresa não encontrada\"}");
        }
        empresasService.delete(empresa.get());
        return ResponseEntity.ok().body("{\"message\":\"ok!\"}");

    }


}
