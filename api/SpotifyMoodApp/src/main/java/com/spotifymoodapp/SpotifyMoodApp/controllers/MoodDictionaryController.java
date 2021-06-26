package com.spotifymoodapp.SpotifyMoodApp.controllers;

import com.spotifymoodapp.SpotifyMoodApp.model.MoodDictionary;
import com.spotifymoodapp.SpotifyMoodApp.model.User;
import com.spotifymoodapp.SpotifyMoodApp.service.MoodDictionaryService;
import com.spotifymoodapp.SpotifyMoodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/mood")
public class MoodDictionaryController {
    @Autowired
    private final MoodDictionaryService moodDictionaryService;
    @Autowired
    private final UserService userService;
    public MoodDictionaryController(MoodDictionaryService moodDictionaryService, UserService userService) {
        this.moodDictionaryService = moodDictionaryService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Set<MoodDictionary>> getMoodDictionariesByUser(@PathVariable("userId") Long userId) {
        User user = userService.findUserById(userId);
        Set<MoodDictionary> moodDictionarySet = moodDictionaryService.getAllDictionariesByUser(user);
        return new ResponseEntity<>(moodDictionarySet, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<MoodDictionary> addMoodDictionary(@RequestBody MoodDictionary moodDictionary) {
        MoodDictionary newMoodDictionary = moodDictionaryService.addMoodDictionary(moodDictionary);
        return new ResponseEntity<>(newMoodDictionary, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<MoodDictionary> updateMoodDictionary(@RequestBody MoodDictionary moodDictionary) {
        MoodDictionary newMoodDictionary = moodDictionaryService.updateMoodDictionary(moodDictionary);
        return new ResponseEntity<>(newMoodDictionary, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMoodDictionary(@PathVariable("id") Long id) {
        moodDictionaryService.deleteMoodDictionaryEntry(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/collection/{collectionId}")
    public ResponseEntity<?> deleteMoodDictionaryCollection(@PathVariable("collectionId") Long collectionId) {
        moodDictionaryService.deleteMoodDictionaryCollection(collectionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
