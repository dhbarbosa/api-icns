package com.br.icns.api.services.user;

import com.br.icns.api.models.User;
import com.br.icns.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.BiConsumer;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return  userRepository.save(user);
    }

    public Boolean existsUserByUsername(String CPF){
        return userRepository.existsUserByUsername(CPF);
    }

    @Transactional
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
