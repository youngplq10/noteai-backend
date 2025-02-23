package dev.starzynski.noteAI_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.starzynski.noteAI_backend.Service.GenerateRandomStringService;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "notes")
public class Note {
    @Id
    private ObjectId id;

    @DBRef
    @JsonIgnoreProperties({"notes", "tags"})
    private User user;

    private String content;

    private String summary;

    @DBRef
    @JsonIgnoreProperties({"notes", "user"})
    private List<Tag> tags;

    @DBRef
    @JsonIgnoreProperties("note")
    private MindMap mindMap;

    private Date createdAtDate;

    private String link;

    public Note() {
        this.id = new ObjectId();
        this.createdAtDate = new Date();
        this.tags = new ArrayList<>();

        GenerateRandomStringService randomStringService = new GenerateRandomStringService();
        this.link = randomStringService.generateRandom(15);
    }

    public ObjectId getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }

    public MindMap getMindMap() { return mindMap; }
    public void setMindMap(MindMap mindMap) { this.mindMap = mindMap; }

    public Date getCreatedAtDate() { return createdAtDate; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
