package kevinhuang.quizapp;

import android.content.Context;

public class MultipleChoiceQuestion extends Question{
    private int mOptionsResIds;
    private int mAnswer; //index into the array of correct answers
    public MultipleChoiceQuestion(int textResId, int hintTextResId, int optionsResIds, int ans) {
        super(textResId, hintTextResId);
        mOptionsResIds = optionsResIds;
        mAnswer = ans;
    }

    public int getOptionsResIds() {
        return mOptionsResIds;
    }

    @Override
    public boolean checkAnswer(int ans){
        return mAnswer == ans;
    }
    @Override
    public boolean isMultipleChoiceQuestion(){
        return true;
    }

    @Override
    public String getAnswerText(Context ctx){
        String[] options = ctx.getResources().getStringArray(mOptionsResIds);
        return options[mAnswer];
    }

}