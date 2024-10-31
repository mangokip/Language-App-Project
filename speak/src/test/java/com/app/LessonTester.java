package com.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LessonTester {
    private Language language = new Language("Spanish");
    private Lesson lesson = new Lesson("test", language);

    @Test
    public void testPlayLesson() {
        int score = lesson.playLesson();
        assertEquals(score, 100);
    }

}
