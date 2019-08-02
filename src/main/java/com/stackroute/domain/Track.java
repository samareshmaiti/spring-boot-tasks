package com.stackroute.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Track {
    //Declare of the variables
    @Id
    private int id;
    private String name;
    private String comment;
//Declare parameterised constructor
    public Track(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }
//Declare null constructor
    public Track() {
    }
//Define of all getter-setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
//Define toString() method
    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", track='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
