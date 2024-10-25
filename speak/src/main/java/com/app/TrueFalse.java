public class TrueFalse extends Question {
    private String word;
    private String translation;
    private boolean isCorrectTranslation;

    // Constructor to initialize TrueFalse question
    public TrueFalse(String word, String correctTranslation, String incorrectTranslation, boolean isCorrect, int difficulty) {
        super("Is this translation correct? " + word + " -> " + (isCorrect ? correctTranslation : incorrectTranslation), difficulty);
        this.word = word;
        this.translation = isCorrect ? correctTranslation : incorrectTranslation;
        this.isCorrectTranslation = isCorrect;
    }

    // Method to check the user's answer (True/False)
    public boolean checkAnswer(String answer) {
        try {
            boolean userAnswer = Boolean.parseBoolean(answer.toLowerCase());
            return userAnswer == isCorrectTranslation;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter 'True' or 'False'.");
            return false;  // Invalid input should return false for incorrect answer
        }
    }

    @Override
    public String toString() {
        return this.getPrompt();
    }
}
