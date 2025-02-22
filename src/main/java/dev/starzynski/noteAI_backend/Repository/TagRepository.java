package dev.starzynski.noteAI_backend.Repository;

import dev.starzynski.noteAI_backend.Model.Tag;
import dev.starzynski.noteAI_backend.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends MongoRepository<Tag, ObjectId> {
    List<Tag> findAllByUser(User user);

    Tag findByNameAndUser(String name, User user);
}
