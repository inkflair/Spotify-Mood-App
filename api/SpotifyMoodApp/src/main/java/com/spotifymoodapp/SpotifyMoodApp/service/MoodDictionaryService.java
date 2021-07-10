package com.spotifymoodapp.SpotifyMoodApp.service;

import com.spotifymoodapp.SpotifyMoodApp.exception.NoDictionariesFoundException;
import com.spotifymoodapp.SpotifyMoodApp.model.MoodDictionary;
import com.spotifymoodapp.SpotifyMoodApp.model.User;
import com.spotifymoodapp.SpotifyMoodApp.repo.MoodDictionaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MoodDictionaryService {
    @Autowired
    private final MoodDictionaryRepo moodDictionaryRepo;

    public MoodDictionaryService(MoodDictionaryRepo moodDictionaryRepo) {
        this.moodDictionaryRepo = moodDictionaryRepo;
    }

    public MoodDictionary addMoodDictionary(MoodDictionary moodDictionary) {
        return moodDictionaryRepo.save(moodDictionary);
    }

    public MoodDictionary updateMoodDictionary(MoodDictionary moodDictionary) {
        return moodDictionaryRepo.save(moodDictionary);
    }

    public Optional<Set<MoodDictionary>> getAllDictionariesByUser(User user) {
        return moodDictionaryRepo.findMoodDictionariesByUser(user);
    }

    public Optional<MoodDictionary> findMoodDictionariesById(Long id) {
        return moodDictionaryRepo.findMoodDictionariesById(id);
    }

    public Optional<Set<MoodDictionary>> findMoodDictionariesByCollectionId(Long collectionId) {
        return moodDictionaryRepo.findMoodDictionariesByCollectionId(collectionId);
    }

    public void deleteMoodDictionaryEntry(Long id) {
        moodDictionaryRepo.deleteMoodDictionaryById(id);
    }

    public void deleteMoodDictionaryCollection(Long collectionId) {
        moodDictionaryRepo.deleteMoodDictionaryByCollectionId(collectionId);
    }
}
