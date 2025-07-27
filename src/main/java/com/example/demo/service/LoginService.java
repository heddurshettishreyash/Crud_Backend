package com.example.demo.service;

import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public boolean registerUser(String username, String password) {
        if (loginRepository.existsByUsername(username)) {
            return false;
        }
        Login login = new Login(username, password);
        loginRepository.save(login);
        return true;
    }

    public boolean login(String username, String password) {
        Login login = loginRepository.findByUsername(username);
        if (login != null && login.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
