package com.hzokbe.eigakan.model.person.response;

public class PersonResponse {
    private String id;

    private String name;

    private String lastName;

    public PersonResponse(String id, String name, String lastName) {
        this.id = id;
        
        this.name = name;

        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
