package com.example.projectliboo.model.entity;

import com.example.projectliboo.model.enums.RoleEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    private String profilePicture;

    @ManyToOne
    private Role role;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> readBooks;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> tbrBooks;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> currentlyReading;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Challenge> challenges;


    public User() {
        this.books=new ArrayList<>();
        this.readBooks=new ArrayList<>();
        this.tbrBooks=new ArrayList<>();
        this.currentlyReading=new ArrayList<>();
    }

    public List<Book> getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(List<Book> currentlyReading) {
        this.currentlyReading = currentlyReading;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(List<Book> readBooks) {
        this.readBooks = readBooks;
    }

    public List<Book> getTbrBooks() {
        return tbrBooks;
    }

    public void setTbrBooks(List<Book> tbrBooks) {
        this.tbrBooks = tbrBooks;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }
}
