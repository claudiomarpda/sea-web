package com.sea.web.seaweb.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer id;

    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String biography;
    private boolean remote;

    @Lob
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address personalAddress;

    @ManyToMany(cascade = CascadeType.ALL)
    @Column(name = "knowledge_id")
    @OrderColumn
    private List<Knowledge> knowledgeList = new ArrayList<>();

    @ElementCollection
    private List<String> usualPlaces = new ArrayList<>();

    public User() {
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Address getPersonalAddress() {
        return personalAddress;
    }

    public void setPersonalAddress(Address personalAddress) {
        this.personalAddress = personalAddress;
    }

    public List<Knowledge> getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List<Knowledge> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    public List<String> getUsualPlaces() {
        return usualPlaces;
    }

    public void setUsualPlaces(List<String> usualPlaces) {
        this.usualPlaces = usualPlaces;
    }
}
