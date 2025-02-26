package org.example.thought;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Thought {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String automaticThought;

    private String currentSituation;

    @Enumerated(EnumType.STRING)
    private ThinkingErrorTypes thinkingErrorType = ThinkingErrorTypes.NONE;

    private String timeOfDay;

    private String mood;

    private int moodIntensity;

}
