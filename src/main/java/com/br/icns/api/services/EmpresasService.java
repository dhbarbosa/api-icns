package com.br.icns.api.services;

import com.br.icns.api.models.Empresas;
import com.br.icns.api.repositories.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresasService {
    @Autowired
    private EmpresasRepository empresasRepository;
    public List<Empresas> findAll() {
        return empresasRepository.findAll();
    }

    public Empresas findEmpresasByCnpj(String cnpj) {
        return empresasRepository.findEmpresasByCnpj(Long.valueOf(cnpj));
    }

    public Empresas findEmpresasByCnpj(Long cnpj) {
        return empresasRepository.findEmpresasByCnpj(cnpj);
    }
    public Boolean existsEmpresasByCpnj(Long cnpj) {
        return empresasRepository.existsEmpresasByCnpj(cnpj);
    }

    public Empresas save(Empresas empresa) {
        return empresasRepository.save(empresa);
    }

    public void delete(Empresas empresas) {
        empresasRepository.delete(empresas);
    }
}
