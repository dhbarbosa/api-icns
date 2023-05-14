package com.br.icns.api.repositories;

import com.br.icns.api.models.Notas;
import com.br.icns.api.services.nota.NotaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotaRepsitory extends JpaRepository<Notas,UUID> {


    boolean existsNotaByKeyNota(String keyNota);

    Notas findNotaByIdNotas(UUID uuid);
}
