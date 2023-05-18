package com.br.icns.api.services.nota;

import com.br.icns.api.models.Notas;
import com.br.icns.api.repositories.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class NotasService {
    @Autowired
    private NotaRepository notaRepository;
    public List<Notas> findAll(){
        return notaRepository.findAll();
    }

    public Notas save(Notas notas){

        return notaRepository.save(notas);
    }

    public boolean existsNotaByKeyNota(String key_nota) {
        return notaRepository.existsNotaByKeyNota(key_nota);
    }


    @Transactional
    public void delete(Notas notas) {
        notaRepository.delete(notas);
    }

    public Notas findNotasByKeyNota(String id) {
        return notaRepository.findNotasByKeyNota(id);
    }
}
