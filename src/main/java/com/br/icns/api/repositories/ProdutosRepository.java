package com.br.icns.api.repositories;

import com.br.icns.api.models.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, UUID> {

    List<Produtos> findAllProdutosByCodProduto(String codProdutodo);

    Produtos findProdutosByIdProdutos(UUID idProduto);

    Produtos findProdutosByCodProduto(String codProdutodo);
}
