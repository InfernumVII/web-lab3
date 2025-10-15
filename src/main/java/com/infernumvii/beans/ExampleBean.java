package com.infernumvii.beans;

import java.io.Serializable;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("example")
public class ExampleBean implements Serializable {
 
    private String name = "Александр";
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}