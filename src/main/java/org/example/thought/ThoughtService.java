package org.example.thought;

import org.example.logger.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThoughtService {
    @Autowired
    private ThoughtRestRepository thoughtRestRepository;
    public List<Thought> getAllThoughts() {

        try{
            List<Thought> allThoughts = thoughtRestRepository.findAll();
            if(allThoughts != null){
                AppLogger.logSuccess("Service Method For Fetching Methods Was Accessed");
                return allThoughts;
            }
        } catch(Error e){
            AppLogger.logError("Error Fetching Thoughts In Service Class",e);
        }
        return null;
    }

    public Thought createThought(Thought thought){

        try{
            Thought newThought = thoughtRestRepository.save(thought);
            AppLogger.logSuccess("Thought Created in Service Successfully");
            return newThought;

        } catch(Error e){
            AppLogger.logError("Error Creating Thought In The Service Class", e);
        }

        return thought;
    }
}
