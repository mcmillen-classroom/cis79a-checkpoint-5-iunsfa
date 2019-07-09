package kevinhuang.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private TextView mScoreTextView;
    private EditText mEditText;

    private LinearLayout mTrueFalseContainer;
    private LinearLayout mFillTheBlankContainer;
    private LinearLayout mMultipleChoiceContainer;

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private Button mHintButton;
    private Button mResetButton;
    private Button mCheckButton;
    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;

    private Button mCheatButton;
    private boolean mCheated;

    private Question[] mQuestions;
    private int mIndex;
    private int mScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mHintButton = (Button) findViewById(R.id.hint_button);
        mResetButton = (Button) findViewById(R.id.reset_button);
        mCheckButton = (Button) findViewById(R.id.check_button);
        mAButton = (Button) findViewById(R.id.a_button);
        mBButton = (Button) findViewById(R.id.b_button);
        mCButton = (Button) findViewById(R.id.c_button);
        mDButton = (Button) findViewById(R.id.d_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);



        mTrueFalseContainer = (LinearLayout) findViewById(R.id.true_false_container);
        mFillTheBlankContainer = (LinearLayout) findViewById(R.id.fill_the_blank_container);
        mMultipleChoiceContainer = (LinearLayout) findViewById(R.id.multiple_choice_container);


        mEditText = (EditText) findViewById(R.id.edit_text);


        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPrevButton.setOnClickListener(this);
        mHintButton.setOnClickListener(this);
        mResetButton.setOnClickListener(this);
        mCheckButton.setOnClickListener(this);
        mAButton.setOnClickListener(this);
        mBButton.setOnClickListener(this);
        mCButton.setOnClickListener(this);
        mDButton.setOnClickListener(this);
        mCheatButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text_view);
        mScoreTextView = (TextView) findViewById(R.id.score_text_view);


        //Init an array of questions
        mQuestions = new Question[7];
        mIndex = 0;
        mScore = 0;

        //Init each slot of the array
        mQuestions[0] = new TrueFalseQuestion(R.string.question_1, true, R.string.hint_1);
        mQuestions[1] = new TrueFalseQuestion(R.string.question_2, true, R.string.hint_2);
        mQuestions[2] = new TrueFalseQuestion(R.string.question_3, false, R.string.hint_3);
        mQuestions[3] = new TrueFalseQuestion(R.string.question_4, true, R.string.hint_4);
        mQuestions[4] = new TrueFalseQuestion(R.string.question_5, false, R.string.hint_5);
        String[] q4Answers = getResources().getStringArray(R.array.question_6_answers);

        mQuestions[5] = new FillTheBlankQuestion(R.string.question_6, R.string.hint_6, q4Answers);
        mQuestions[6] = new MultipleChoiceQuestion(R.string.question_7,R.string.hint_7, R.array.question_7_answers,1);


        //go to whichever question is represented by mIndex
        //setup the first question
        setupQuestion();
        mScoreTextView.setText("Score: " + mScore);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.true_button) {
            checkAnswer(true);


        } else if (view.getId() == R.id.false_button) {
            checkAnswer(false);

        }
        else if(view.getId() == R.id.check_button) {
            checkAnswer(mEditText.getText().toString());
        }
        else if (view.getId() == R.id.prev_button) {
            mIndex--;


        } else if (view.getId() == R.id.next_button) {
            //change to the next question
            //increment the index by 1
            mIndex++;
            mFalseButton.setEnabled(true);
            mTrueButton.setEnabled(true);
            mAButton.setEnabled(true);
            mCButton.setEnabled(true);
            mDButton.setEnabled(true);
            mBButton.setEnabled(true);
            mAButton.setBackgroundResource(android.R.drawable.btn_default);
            mBButton.setBackgroundResource(android.R.drawable.btn_default);
            mCButton.setBackgroundResource(android.R.drawable.btn_default);
            mDButton.setBackgroundResource(android.R.drawable.btn_default);


            //do if statement here
            // if the mIndex is greater than equal 
            //change text in view


        } else if (view.getId() == R.id.hint_button) {
            Toast myToast2 = Toast.makeText(this, mQuestions[mIndex].getmHintTextResId(), Toast.LENGTH_LONG);
            myToast2.show();
        } else if (view.getId() == R.id.reset_button) {
            mScore = 0;
            mScoreTextView.setText("Score: " + mScore);
        }
          else if(view.getId() == R.id.a_button){
            if(checkAnswer(0)){
                mAButton.setBackgroundColor(Color.GREEN);
                mAButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);
                mBButton.setEnabled(false);

            }
            else{
                mAButton.setBackgroundColor(Color.RED);
                mAButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);
                mBButton.setEnabled(false);
            }


        }
        else if(view.getId() == R.id.b_button){
            if(checkAnswer(1)){
                mBButton.setBackgroundColor(Color.GREEN);
                mAButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);
                mBButton.setEnabled(false);

            }
            else{
                mBButton.setBackgroundColor(Color.RED);
                mAButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);
                mBButton.setEnabled(false);
            }


        }
        else if(view.getId() == R.id.c_button){
            if(checkAnswer(2)){
                mCButton.setBackgroundColor(Color.GREEN);
                mAButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);
                mBButton.setEnabled(false);

            }
            else{
                mCButton.setBackgroundColor(Color.RED);
                mAButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);
                mBButton.setEnabled(false);
            }
        }
        else if(view.getId() == R.id.d_button){
            if(checkAnswer(3)){
                mDButton.setBackgroundColor(Color.GREEN);
                mAButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);
                mBButton.setEnabled(false);

            }
            else{
                mDButton.setBackgroundColor(Color.RED);
                mAButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);
                mBButton.setEnabled(false);
            }
        }
        else if(view.getId() == R.id.cheat_button){
            //TODO: launch cheat activity
            Intent cheatIntent = CheatActivity.newIntent(this);
            startActivity(cheatIntent);

        }
        if (mIndex > mQuestions.length-1 || mIndex < 0) {
            mIndex = 0;

        }


        setupQuestion();


    }


    public void setupQuestion() {
        mTextView.setText(mQuestions[mIndex].getTextResId());
        if (mQuestions[mIndex].isTrueFalseQuestion()) {
            mTrueFalseContainer.setVisibility(View.VISIBLE);
            mFillTheBlankContainer.setVisibility(View.GONE);
            mMultipleChoiceContainer.setVisibility(View.GONE);
        } else if (mQuestions[mIndex].isFillTheBlankQuestion()) {
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility(View.VISIBLE);
            mMultipleChoiceContainer.setVisibility(View.GONE);

        }
        else if (mQuestions[mIndex].isMultipleChoiceQuestion()){
            //hide and show relevance containers

            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility(View.GONE);
            mMultipleChoiceContainer.setVisibility(View.VISIBLE);
            int optionsResId = ((MultipleChoiceQuestion) mQuestions[mIndex]).getOptionsResIds();
            String[] options = getResources().getStringArray(optionsResId);
            //TODO: use options array to set the text of each Multiple choice button
            mAButton.setText(options[0]);
            mBButton.setText(options[1]);
            mCButton.setText(options[2]);
            mDButton.setText(options[3]);
            //Index 0 is for button A
            // Index 3 is for button D
        }
    }


    public boolean checkAnswer(boolean userInput) {
        if (mQuestions[mIndex].checkAnswer(userInput)) {
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
            mScore++;
            mScoreTextView.setText("Score: " + mScore);
            Toast myToast = Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT);
            myToast.show();
            return true;


        } else {
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
            mScore--;
            mScoreTextView.setText("Score: " + mScore);
            Toast myToast = Toast.makeText(this, "You are incorrect", Toast.LENGTH_SHORT);
            myToast.show();
            return false;

        }
    }
        public boolean checkAnswer(String userInput){
            if (mQuestions[mIndex].checkAnswer(userInput)) {
                mFalseButton.setEnabled(false);
                mTrueButton.setEnabled(false);
                mScore++;
                mScoreTextView.setText("Score: " + mScore);
                Toast myToast = Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT);
                myToast.show();
                return true;


            } else {
                mFalseButton.setEnabled(false);
                mTrueButton.setEnabled(false);
                mScore--;
                mScoreTextView.setText("Score: " + mScore);
                Toast myToast = Toast.makeText(this, "You are incorrect", Toast.LENGTH_SHORT);
                myToast.show();
                return false;

            }

        }
    public boolean checkAnswer(int userInput){
        if (mQuestions[mIndex].checkAnswer(userInput)) {

            mScore++;
            mScoreTextView.setText("Score: " + mScore);
            Toast myToast = Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT);
            myToast.show();
            return true;


        } else {

            mScore--;
            mScoreTextView.setText("Score: " + mScore);
            Toast myToast = Toast.makeText(this, "You are incorrect", Toast.LENGTH_SHORT);
            myToast.show();
            return false;

        }

    }


    }

