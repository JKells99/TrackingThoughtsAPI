package org.example.controllers;

import org.example.entities.Thought;
import org.example.logger.AppLogger;
import org.example.service.ThoughtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/allthoughts")
public class ThoughtController {



    @Autowired
    private ThoughtService thoughtService;

    @GetMapping
    public ResponseEntity<List<Thought>> getThoughts() {
        List<Thought> allThoughts = thoughtService.getAllThoughts();

        try{
            if(allThoughts!= null){
                AppLogger.logSuccess("Fetched A List Of All Thoughts");
                return ResponseEntity.ok(allThoughts);
            }
        } catch(Error  e){
            AppLogger.logError("Could Not Fetch The List If Thoughts",e);


        }


        return null;
    }

   @PostMapping("/post")
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
