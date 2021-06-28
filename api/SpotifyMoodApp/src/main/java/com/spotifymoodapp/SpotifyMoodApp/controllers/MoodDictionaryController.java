package com.spotifymoodapp.SpotifyMoodApp.controllers;

import com.spotifymoodapp.SpotifyMoodApp.model.MoodDictionary;
import com.spotifymoodapp.SpotifyMoodApp.model.User;
import com.spotifymoodapp.SpotifyMoodApp.service.MoodDictionaryService;
import com.spotifymoodapp.SpotifyMoodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/mood")
public class MoodDictionaryController {
    @Autowired
    private MoodDictionaryService moodDictionaryService;
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Set<MoodDictionary>> getMoodDictionariesByUser(@PathVariable("userId") Long userId) {
        Optional<User> optionalUser = userService.findUserById(userId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Set<MoodDictionary>> moodDictionarySet = moodDictionaryService.getAllDictionariesByUser(optionalUser.get());
        if (!moodDictionarySet.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return new ResponseEntity<>(moodDictionarySet.get(), HttpStatus.OK);
    }

    @GetMapping("/collections/{userId}/{collectionId}")
    public ResponseEntity<Set<MoodDictionary>> getMoodDictionariesByCollection(@PathVariable("userId") Long userId, @PathVariable("collectionId") Long collectionId) {
        Optional<User> optionalUser = userService.findUserById(userId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Set<MoodDictionary>> optionalMoodDictionarySet = moodDictionaryService.findMoodDictionariesByCollectionId(collectionId);
        if (!optionalMoodDictionarySet.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return new ResponseEntity<>(optionalMoodDictionarySet.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<MoodDictionary> addMoodDictionary(@RequestBody MoodDictionary moodDictionary) {
        Optional<User> optionalUser = userService.findUserById(moodDictionary.getUser().getId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        moodDictionary.setUser(optionalUser.get());
        MoodDictionary savedMoodDictionary = moodDictionaryService.addMoodDictionary(moodDictionary);
        return new ResponseEntity<>(savedMoodDictionary, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<MoodDictionary> updateMoodDictionary(@RequestBody MoodDictionary moodDictionary) {
        Optional<User> optionalUser = userService.findUserById(moodDictionary.getUser().getId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<MoodDictionary> optionalMoodDictionary = moodDictionaryService.findMoodDictionariesById(moodDictionary.getId());
        if (!optionalMoodDictionary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        MoodDictionary newMoodDictionary = moodDictionaryService.updateMoodDictionary(optionalMoodDictionary.get());
        return new ResponseEntity<>(newMoodDictionary, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMoodDictionary(@PathVariable("id") Long id) {
        Optional<MoodDictionary> optionalMoodDictionary = moodDictionaryService.findMoodDictionariesById(id);
        if (!optionalMoodDictionary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<User> optionalUser = userService.findUserById(optionalMoodDictionary.get().getUser().getId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        moodDictionaryService.deleteMoodDictionaryEntry(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/collection/{collectionId}")
    public ResponseEntity<?> deleteMoodDictionaryCollection(@PathVariable("collectionId") Long collectionId) {
        moodDictionaryService.deleteMoodDictionaryCollection(collectionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
