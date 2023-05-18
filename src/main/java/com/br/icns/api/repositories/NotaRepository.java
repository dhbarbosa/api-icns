package com.br.icns.api.repositories;

import com.br.icns.api.models.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotaRepository extends JpaRepository<Notas,UUID> {


    boolean existsNotaByKeyNota(String keyNota);

    Notas findNotasByKeyNota(String id);
}
