package com.montezumadev.parkingsystem.service;

import com.montezumadev.parkingsystem.entity.User;
import com.montezumadev.parkingsystem.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<String> createUser(User user) {
        user.setLoyaltyCard(true);
        if (userRepository.findUserByDocument(user.getDocument()) != null) {
            return ResponseEntity.badRequest().body("Este documente já pertence à outro cliente.");
        }

        User newUser = userRepository.save(user);
        return ResponseEntity.ok("Usuário cadastrado com sucesso! ");
    }

    public ResponseEntity<String> getUserByDocument(String document) {

        User user = userRepository.findUserByDocument(document);
        if (user != null) {
            return ResponseEntity.ok("Usuário encontrado: " + user.getName());
        } else {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }
    }


}
