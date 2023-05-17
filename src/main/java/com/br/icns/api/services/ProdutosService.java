package com.br.icns.api.services;


import com.br.icns.api.models.Produtos;
import com.br.icns.api.repositories.NotaRepsitory;
import com.br.icns.api.repositories.ProdutosRepository;
import com.br.icns.api.services.nota.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {
    @Autowired
    private ProdutosRepository produtosRepository;
    public List<Produtos> findAll() {
        return produtosRepository.findAll();
    }
}
