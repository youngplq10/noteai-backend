package dev.starzynski.noteAI_backend.Model;

import java.util.List;

public class NoteRequestDTO {
    private String username;
    private String content;
    private List<String> tags;

    public NoteRequestDTO(String username, String content, List<String> tags) {
        this.username = username;
        this.content = content;
        this.tags = tags;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}
