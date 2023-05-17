package com.br.icns.api.repositories;

import com.br.icns.api.models.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutosRepository extends JpaRepository<Produtos, UUID> {

}
