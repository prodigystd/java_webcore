package com.WebCore.model;
import java.util.Set;

public class Role {
    private int id;
    private String name;
    private Set<User> users;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
