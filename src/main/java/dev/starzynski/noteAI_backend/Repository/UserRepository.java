package dev.starzynski.noteAI_backend.Repository;

import dev.starzynski.noteAI_backend.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
