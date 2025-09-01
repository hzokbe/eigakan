package com.hzokbe.eigakan.model.user;

import com.hzokbe.eigakan.model.role.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;

    private String email;

    private String passwordHash;

    private Role role = Role.USER;

    public User() {
    }

    public User(String username, String email, String passwordHash) {
        this.id = UUID.randomUUID().toString();

        this.username = username;

        this.email = email;

        this.passwordHash = passwordHash;
    }

    public User(String username, String email, String passwordHash, Role role) {
        this.id = UUID.randomUUID().toString();

        this.username = username;

        this.email = email;

        this.passwordHash = passwordHash;

        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
