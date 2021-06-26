package com.spotifymoodapp.SpotifyMoodApp.service;

import com.spotifymoodapp.SpotifyMoodApp.exception.UserNotFoundException;
import com.spotifymoodapp.SpotifyMoodApp.model.User;
import com.spotifymoodapp.SpotifyMoodApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(User user) { return userRepo.save(user); }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found"));
    }

    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }
}
