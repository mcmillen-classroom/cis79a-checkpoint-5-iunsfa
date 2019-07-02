package kevinhuang.quizapp;

public class TrueFalseQuestion extends Question {
    private boolean mAnswer;

    public TrueFalseQuestion(int textResId, boolean ans, int hintTextResId) {
        super(textResId, hintTextResId);
        mAnswer = ans;
    }

    @Override
    public boolean checkAnswer(boolean ans){
        return mAnswer == ans;
    }

    @Override
    public boolean isTrueFalseQuestion(){
        return true;
    }

}
