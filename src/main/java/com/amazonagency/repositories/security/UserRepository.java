package com.amazonagency.repositories.security;


import com.amazonagency.model.security.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByUsername(String username);

    @Query("{username : ?0}")
    Optional<User> existsByUsername(String username);

    @Query("{email : ?0}")
    Optional<User> existsByEmail(String email);
}
