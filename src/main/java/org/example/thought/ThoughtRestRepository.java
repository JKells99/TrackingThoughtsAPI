package org.example.thought;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "thought", path = "thought")
public interface ThoughtRestRepository  extends JpaRepository<Thought, Long> {
}
