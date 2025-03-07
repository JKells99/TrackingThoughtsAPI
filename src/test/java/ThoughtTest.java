import org.example.thought.ThinkingErrorTypes;
import org.example.thought.Thought;
import org.example.thought.ThoughtRestRepository;
import org.example.thought.ThoughtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ThoughtTest {

    @InjectMocks
    private ThoughtService thoughtService;

    @Mock
    private ThoughtRestRepository thoughtRestRepository;

    public ThoughtTest() {
        MockitoAnnotations.initMocks(this);
    }





    @Test
    @DisplayName("Testing Thought Creation")
    public void testThoughtCreate(){

        Thought thought = new Thought();

        thought.setCurrentSituation("Test Situation");
        thought.setAutomaticThought("Test Thought");
        thought.setMood("Anxious");
        thought.setMoodIntensity(60);
        thought.setTimeOfDay("Day");
        thought.setThinkingErrorType(ThinkingErrorTypes.MIND_READING);

        List<Thought> thoughtList = Arrays.asList(thought);
        Assertions.assertTrue(thoughtList.contains(thought));
    }

    @Test
   public void testGettingListOfThoughts(){
        Thought thought = new Thought();
        Thought thought2 = new Thought();

        thought.setId(1L);
        thought.setCurrentSituation("Test Situation");
        thought.setAutomaticThought("Test Thought");
        thought.setMood("Anxious");
        thought.setMoodIntensity(60);
        thought.setTimeOfDay("Day");
        thought.setThinkingErrorType(ThinkingErrorTypes.EMOTIONAL_REASONING);

        thought2.setId(2L);
        thought2.setCurrentSituation("Test Situation2");
        thought2.setAutomaticThought("Test Thought2");
        thought2.setMood("Sad");
        thought2.setMoodIntensity(60);
        thought2.setTimeOfDay("Day");
        thought2.setThinkingErrorType(ThinkingErrorTypes.LABELING);

        List<Thought> thoughtList = Arrays.asList(thought,thought2);


        when(thoughtService.getAllThoughts()).thenReturn(thoughtList);


        List<Thought> result = thoughtService.getAllThoughts();

        Assertions.assertEquals(thought,result.get(0));
        Assertions.assertEquals(thought2,result.get(1));
        Assertions.assertNotEquals(3,result.size());






    }

}
