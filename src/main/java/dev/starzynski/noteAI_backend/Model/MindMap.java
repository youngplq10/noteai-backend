package dev.starzynski.noteAI_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mindmaps")
public class MindMap {
    @Id
    private ObjectId id;

    @DBRef
    @JsonIgnoreProperties({"user", "tags"})
    private Note note;

    private String link;

    public MindMap() {
        this.id = new ObjectId();
    }

    public ObjectId getId() { return id; }

    public Note getNote() { return note; }
    public void setNote(Note note) { this.note = note; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
