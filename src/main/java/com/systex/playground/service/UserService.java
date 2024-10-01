package com.systex.playground.service;

import com.systex.playground.model.Member;
import com.systex.playground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public boolean validateUserCredentials(String username, String password) {
        Member member = userRepository.findByUsername(username);
        return member != null && member.getPassword().equals(password);
    }

    public Member registerNewUser(Member member) {
        return userRepository.save(member);
    }

    public Member findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
