package org.example.repositories;

import org.example.entities.Thought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "thought", path = "thought", exported = false)
public interface ThoughtRestRepository  extends JpaRepository<Thought, Long> {
}
