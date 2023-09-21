package com.example.proiectcolectiv.model;

import java.util.List;
import java.util.Map;

public class UserPremium2 {
    private String uid;
    private String photoURL;
    private String displayName;
    public static class Stats{
        private Integer questions;
        private Integer likes;
        private Integer dislikes;

        public Stats(Integer questions, Integer likes, Integer dislikes) {
            this.questions = questions;
            this.likes = likes;
            this.dislikes = dislikes;
        }
        public Stats(){}
        public Integer getQuestions() {
            return questions;
        }

        public void setQuestions(Integer questions) {
            this.questions = questions;
        }

        public Integer getLikes() {
            return likes;
        }

        public void setLikes(Integer likes) {
            this.likes = likes;
        }

        public Integer getDislikes() {
            return dislikes;
        }

        public void setDislikes(Integer dislikes) {
            this.dislikes = dislikes;
        }
    }
    private UserPremium.Stats stats;
    Map<String, List<String>> badges;

    List<Question> questions;
    List<Question> favorites;

    public UserPremium2(String uid, String photoURL, String displayName, UserPremium.Stats stats, Map<String, List<String>> badges, List<Question> questions, List<Question> favorites) {
        this.uid = uid;
        this.photoURL = photoURL;
        this.displayName = displayName;
        this.stats = stats;
        this.badges = badges;
        this.questions = questions;
        this.favorites = favorites;
    }
    public UserPremium2(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UserPremium.Stats getStats() {
        return stats;
    }

    public void setStats(UserPremium.Stats stats) {
        this.stats = stats;
    }

    public Map<String, List<String>> getBadges() {
        return badges;
    }

    public void setBadges(Map<String, List<String>> badges) {
        this.badges = badges;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Question> favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "UserPremium2{" +
                "uid='" + uid + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", displayName='" + displayName + '\'' +
                ", stats=" + stats +
                ", badges=" + badges +
                ", questions=" + questions +
                ", favorites=" + favorites +
                '}';
    }
}
