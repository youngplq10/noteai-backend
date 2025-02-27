package dev.starzynski.noteAI_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;

    private String username;

    private String email;

    private String password;

    private Boolean newsletter;

    private Date createdAtDate;

    @DBRef
    @JsonIgnoreProperties("user")
    private List<Note> notes;

    @DBRef
    @JsonIgnoreProperties()
    private List<Tag> tags;

    private String role;

    public User() {
        this.id = new ObjectId();
        this.createdAtDate = new Date();
        this.role = "USER";
        this.notes = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public ObjectId getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getNewsletter() { return newsletter; }
    public void setNewsletter(Boolean newsletter) { this.newsletter = newsletter; }

    public Date getCreatedAtDate() { return createdAtDate; }

    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }

    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }

    public String getRole() { return role; }
}
