package org.example.thought;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
                log.info("Fetched A List Of All Thoughts");
                return ResponseEntity.ok(allThoughts);
            }
        } catch(Error  e){
            log.error("Could Not Fetch The List If Thoughts",e);


        }


        return new ResponseEntity<>("No Thoughts Found", HttpStatus.NO_CONTENT);
    }

   @PostMapping("/addNewThought")
    public ResponseEntity<Thought> addANewThought(@RequestBody Thought thought){

        if(thought != null){
            try{
                Thought newThought = thoughtService.createThought(thought);
                log.info("Created A New Thought");
                return ResponseEntity.ok(newThought);
            } catch (Error e){
                log.error("Cannot Create A New Thought",e);

            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return null;
   }




}
