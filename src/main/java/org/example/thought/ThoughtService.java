package org.example.thought;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ThoughtService {
    @Autowired
    private ThoughtRestRepository thoughtRestRepository;
    public List<Thought> getAllThoughts() {

        try{
            List<Thought> allThoughts = thoughtRestRepository.findAll();
            log.info("Service Method For Fetching Methods Was Accessed");
            return allThoughts;
        } catch(Error e){
            log.error("Error Fetching Thoughts In Service Class",e);
        }
        return null;
    }

    public Thought createThought(Thought thought){

        try{
            Thought newThought = thoughtRestRepository.save(thought);
            log.info("Service Method For Creating Thought Was Accessed");
            return newThought;

        } catch(Error e){

            log.error("Error Creating Thought In The Service Class", e);
        }

        return thought;
    }
}
