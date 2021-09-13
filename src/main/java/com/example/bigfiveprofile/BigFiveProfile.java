package com.example.bigfiveprofile;

import java.util.ArrayList;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class BigFiveProfile implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private String email;

    @Column(length=1000) //overcome default VARCHAR(255)
    private ArrayList<Domain> domains;
    /* Constructors */
    public BigFiveProfile(String name, String email, ArrayList<Domain> domains) {
        this.name = name;
        this.email = email;
        this.domains = domains;
    }
    public BigFiveProfile() {
        this.name = null;
        this.email = null;
    }

    /* Getters */
    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    /* Setters */
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    /* misc*/
    @Override
    public String toString(){
        String representation = this.name + " AT " + this.email;
        for(Domain d : this.domains){
            representation += d.toString() + "\n-----\n";
        }
        return representation;
    }

}
