package com.spotifymoodapp.SpotifyMoodApp.repo;

import com.spotifymoodapp.SpotifyMoodApp.model.MoodDictionary;
import com.spotifymoodapp.SpotifyMoodApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface MoodDictionaryRepo extends JpaRepository<MoodDictionary, Long> {
    void deleteMoodDictionaryById(Long id);
    void deleteMoodDictionaryByCollectionId(Long collectionId);

    Optional<Set<MoodDictionary>> findMoodDictionariesByUser(User user);
    Optional<MoodDictionary> findMoodDictionariesById(Long id);
    Optional<Set<MoodDictionary>> findMoodDictionariesByCollectionId(Long id);
}
