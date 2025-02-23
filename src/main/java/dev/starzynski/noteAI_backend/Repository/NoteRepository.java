package dev.starzynski.noteAI_backend.Repository;

import dev.starzynski.noteAI_backend.Model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, ObjectId> {
    Note findByLink(String link);
}
