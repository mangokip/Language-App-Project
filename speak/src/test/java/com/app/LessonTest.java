package com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class LessonTest {

    private Lesson lesson;
    private Language language;

    @BeforeEach
    void setUp() {
        language = new Language("Spanish");
        lesson = new Lesson("Animals", language);
    }

    @Test
    void testLessonInitialization() {
        assertEquals(5, lesson.getQuestions().size());
        assertTrue(lesson.getQuestions().get(0) instanceof MultipleChoice);
        assertTrue(lesson.getQuestions().get(1) instanceof MultipleChoice);
        assertTrue(lesson.getQuestions().get(2) instanceof TrueFalse);
        assertTrue(lesson.getQuestions().get(3) instanceof TrueFalse);
        assertTrue(lesson.getQuestions().get(4) instanceof FillBlank);
    }

    @Test
    void testPlayLessonWithCorrectAnswers() {
        Scanner scanner = new Scanner("1\n1\n1\n1\n1\n");
        for (Question question : lesson.getQuestions()) {
            assertTrue(question.askQuestion(scanner));
        }
        int score = lesson.playLesson();
        assertEquals(100, score);
    }

    @Test
    void testPlayLessonWithIncorrectAnswers() {
        Scanner scanner = new Scanner("0\n0\n0\n0\n0\n");
        for (Question question : lesson.getQuestions()) {
            assertFalse(question.askQuestion(scanner));
        }
        int score = lesson.playLesson();
        assertEquals(0, score);
    }

    @Test
    void testPlayLessonWithMixedAnswers() {
        Scanner scanner = new Scanner("1\n1\n1\n0\n0\n");
        int correctCount = 0;
        for (int i = 0; i < lesson.getQuestions().size(); i++) {
            boolean isCorrect = lesson.getQuestions().get(i).askQuestion(scanner);
            if (i < 3) {
                assertTrue(isCorrect);
                correctCount++;
            } else {
                assertFalse(isCorrect);
            }
        }
        int score = lesson.playLesson();
        assertEquals((correctCount * 100) / 5, score);
    }
}
