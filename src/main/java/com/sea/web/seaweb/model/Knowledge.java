package com.sea.web.seaweb.model;

import javax.persistence.*;

@Entity
public class Knowledge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "knowledge_id")
    private Integer id;
    private String title;
    private String description;

    public Knowledge() {
    }

    public Knowledge(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
