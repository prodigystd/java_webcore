package com.WebCore.model;
import java.util.Set;

public class Role {
    private Long id;
    private String name;
    private Set<User> users;

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
