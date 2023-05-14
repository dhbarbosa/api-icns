package com.br.icns.api.repositories;

import com.br.icns.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User , UUID> {

    Boolean existsUserByUsername(String username);

    User findByUsername(String username);


}
