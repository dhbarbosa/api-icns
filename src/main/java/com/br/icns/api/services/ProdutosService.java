package com.br.icns.api.services;


import com.br.icns.api.models.Produtos;
import com.br.icns.api.repositories.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutosService {
    @Autowired
    private ProdutosRepository produtosRepository;
    public List<Produtos> findAll() {
        return produtosRepository.findAll();
    }

    public List<Produtos> findAllProdutosByCodProduto(String codProdutodo) {
        return produtosRepository.findAllProdutosByCodProduto(codProdutodo);
    }

    public Produtos findProdutosByCodProduto(String codProdutodo) {
        return produtosRepository.findProdutosByCodProduto(codProdutodo);
    }


    public Produtos save(Produtos newProduto) {
        return produtosRepository.save(newProduto);
    }

    public void delete(Produtos produtos) {
        produtosRepository.delete(produtos);
    }

    public Produtos findProdutosByIdProdutos(UUID idProduto) {
       return produtosRepository.findProdutosByIdProdutos(idProduto);
    }
}
