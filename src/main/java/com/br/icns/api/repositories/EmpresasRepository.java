package com.br.icns.api.repositories;

import com.br.icns.api.models.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresasRepository extends JpaRepository<Empresas, Long> {
    Empresas findEmpresasByCnpj(Long cnpj);


    Boolean existsEmpresasByCnpj(Long cnpj);
}
