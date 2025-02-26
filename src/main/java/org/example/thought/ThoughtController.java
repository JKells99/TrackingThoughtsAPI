package org.example.thought;

import org.example.logger.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/allthought")
public class ThoughtController {



    @Autowired
    private ThoughtService thoughtService;


    @GetMapping("/getAllThoughts")
    public ResponseEntity<?> getThoughts() {
        List<Thought> allThoughts = thoughtService.getAllThoughts();

        try{
            if(allThoughts!= null){
                AppLogger.logSuccess("Fetched A List Of All Thoughts");
                return ResponseEntity.ok(allThoughts);
            }
        } catch(Error  e){
            AppLogger.logError("Could Not Fetch The List If Thoughts",e);


        }


        return new ResponseEntity<>("No Thoughts Found", HttpStatus.NO_CONTENT);
    }

   @PostMapping("/addNewThought")
    public ResponseEntity<Thought> addANewThought(@RequestBody Thought thought){

        if(thought != null){
            try{
                Thought newThought = thoughtService.createThought(thought);
                AppLogger.logSuccess("Thought Created Successfully");
                return ResponseEntity.ok(newThought);
            } catch (Error e){
                AppLogger.logError("Cannot Create A New Thought",e);
                e.printStackTrace();
                e.getCause();
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return null;
   }




}
