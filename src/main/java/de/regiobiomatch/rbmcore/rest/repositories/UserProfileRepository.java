package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.userprofile.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    Optional<UserProfile> findByPersonEmail(String email);
    boolean existsByPersonEmail(String email);
}