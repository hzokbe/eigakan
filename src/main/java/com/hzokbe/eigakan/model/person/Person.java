package com.hzokbe.eigakan.model.person;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "people")
public class Person {
    @Id
    private String id;

    private String name;

    private String lastName;

    public Person() {
    }

    public Person(String name, String lastName) {
        this.id = UUID.randomUUID().toString();

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
