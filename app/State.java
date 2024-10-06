public interface State {
    
    public void increaseLevel();
    public void decreaseLevel();
    public ArrayList<Question> getQuestionPool();
    public void loadContent();
}

