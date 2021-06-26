package com.spotifymoodapp.SpotifyMoodApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="MoodDictionaries")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MoodDictionary implements Serializable {
    public MoodDictionary() {    }

    public MoodDictionary(Long id, User user, String mood, String spotifyLink, Long collectionId) {
        this.id = id;
        this.user = user;
        this.mood = mood;
        this.spotifyLink = spotifyLink;
        this.entryId = collectionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getSpotifyLink() {
        return spotifyLink;
    }

    public void setSpotifyLink(String spotifyLink) {
        this.spotifyLink = spotifyLink;
    }

    public Long getCollectionId() {
        return entryId;
    }

    public void setCollectionId(Long entryId) {
        this.entryId = entryId;
    }
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    // the mood that will be associated
    private String mood;
    private String spotifyLink;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, updatable = false)
    private Long entryId;
}
