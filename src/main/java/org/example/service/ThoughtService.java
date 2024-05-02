package org.example.service;

import org.example.entities.Thought;
import org.example.repositories.ThoughtRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThoughtService {

    @Autowired
    private ThoughtRestRepository thoughtRestRepository;

    public List<Thought> getAllThoughts() {
        List<Thought> allThoughts = thoughtRestRepository.findAll();
        return allThoughts;
    }

    public Thought createThought(Thought thought){
        Thought newThought = thoughtRestRepository.save(thought);

        return newThought;
    }
}
