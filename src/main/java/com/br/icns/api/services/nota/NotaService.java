package com.br.icns.api.services.nota;

import com.br.icns.api.models.Notas;
import com.br.icns.api.repositories.NotaRepsitory;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class NotaService {
    @Autowired
    private NotaRepsitory notaRepsitory;
    public List<Notas> findAll(){
        return notaRepsitory.findAll();
    }

    public Notas save(Notas notas){

        return notaRepsitory.save(notas);
    }

    public boolean existsNotaByKeyNota(String key_nota) {
        return notaRepsitory.existsNotaByKeyNota(key_nota);
    }

    public Notas findNotaByIdNotas(UUID uuid) {
        return notaRepsitory.findNotaByIdNotas(uuid);
    }

    @Transactional
    public void delete(Notas notas) {
        notaRepsitory.delete(notas);
    }

    public Notas findNotasByKeyNota(String id) {
        return notaRepsitory.findNotasByKeyNota(id);
    }
}
