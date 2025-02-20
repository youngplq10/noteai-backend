package dev.starzynski.noteAI_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "tags")
public class Tag {
    @Id
    private ObjectId id;

    private String name;

    @DBRef
    @JsonIgnoreProperties({"notes", "tags"})
    private User user;

    @DBRef
    @JsonIgnoreProperties({"user", "tags"})
    private List<Note> notes;

    public Tag() {
        this.id = new ObjectId();
    }

    public ObjectId getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }
}
