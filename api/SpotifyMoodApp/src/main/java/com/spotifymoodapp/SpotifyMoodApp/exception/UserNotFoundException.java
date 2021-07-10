package com.spotifymoodapp.SpotifyMoodApp.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s) {
        super(s);
    }
}