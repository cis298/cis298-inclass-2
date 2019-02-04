package edu.kvcc.cis298.geoquiz;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    // Define an array of the questions for the app
    // Each instance of question will store the integer from
    // the R file for the specific string. It is not storing
    // the questions string as a string, but instead the resource
    // identifier for the string in strings.xml and R.
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    // A variable to keep track of what question we are on.
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Log out that onCreate was called
        Log.d(TAG, "onCreate(Bundle) called");
        // Run the parent work
        super.onCreate(savedInstanceState);
        // Set the view for this activity to the activity_quiz layout
        setContentView(R.layout.activity_quiz);

        // Check to see if there is a savedInstanceState that was passed
        // into this onCreate method. If so, get the current question
        // index from the savedInstanceState and use it t set
        // the current index.
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        // Get a reference to the question textview
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        // Call the updateQuestion method to move to the next question
        updateQuestion();


        // Get a reference to the true button from the layout.
        mTrueButton = (Button) findViewById(R.id.true_button);
        // Setup and onclick listener for the true button.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                // Call the updateQuestion method to move to the next question
                updateQuestion();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        // Log out that onStart was called
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Log out that onStop was called
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Log out that onDestroy was called
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Log out that onPause was called
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Log out that onResume was called
        Log.d(TAG, "onResume() called");
    }

    // This is an override method that we use to save any information
    // that we need to retain during screen rotation. When the app
    // enters the paused state, this method will fire. We can store
    // data into the Bundle object that it gets. Then pull that data
    // back out in the onCreate method.
    // The method has methods that are type specific. So, here we
    // are using putInt to put an integer. There are other put methods
    // for the other types.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState called");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void updateQuestion() {
        // Pull the resId for the first question from the question bank
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        // Set the text for the textview to the resId of the question.
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(
                this,
                messageResId,
                Toast.LENGTH_SHORT
        ).show();
    }
}
