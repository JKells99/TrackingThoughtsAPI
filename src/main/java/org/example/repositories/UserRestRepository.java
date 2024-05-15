package org.example.repositories;


import org.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user", exported = false)
public interface UserRestRepository extends JpaRepository<User, Long> {

    User findByuserName(String userName);
}
