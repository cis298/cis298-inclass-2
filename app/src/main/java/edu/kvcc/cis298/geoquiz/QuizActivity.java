package edu.kvcc.cis298.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

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
        // Run the parent work
        super.onCreate(savedInstanceState);
        // Set the view for this activity to the activity_quiz layout
        setContentView(R.layout.activity_quiz);

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
