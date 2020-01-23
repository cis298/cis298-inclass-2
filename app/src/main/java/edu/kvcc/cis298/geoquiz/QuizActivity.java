package edu.kvcc.cis298.geoquiz;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    // For submission of answer.
    private Button mSubmitButton;
    // To move to next qustion.
    private Button mNextButton;
    // For question text.
    private TextView mQuestionTextView;
    // For fetching selected answer
    private RadioGroup mQuestionGroup;
    // For setting choice text of each choice
    private RadioButton mChoice1;
    private RadioButton mChoice2;
    private RadioButton mChoice3;
    private RadioButton mChoice4;

    // Define an array of the questions for the app
    // Each instance of question will store the integer from
    // the R file for the specific string. It is not storing
    // the questions string as a string, but instead the resource
    // identifier for the string in strings.xml and R.
    private Question[] mQuestionBank = new Question[] {
            new Question(
                    R.string.question_1_multiple, // Question Text
                    R.id.multiple_choice_3, // Question Correct Radio Button Id
                    new int[] {
                            R.string.question_1_choice_1, // Each Radio Button Text
                            R.string.question_1_choice_2,
                            R.string.question_1_choice_3,
                            R.string.question_1_choice_4
                    }
            ),
            new Question(
                    R.string.question_2_multiple, // Question Text
                    R.id.multiple_choice_2, // Question Correct Radio Button Id
                    new int[] {
                            R.string.question_2_choice_1, // Each Radio Button Text
                            R.string.question_2_choice_2,
                            R.string.question_2_choice_3,
                            R.string.question_2_choice_4
                    }
            )
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

        // Get a reference to the QuestionGroup
        mQuestionGroup = (RadioGroup) findViewById(R.id.multiple_group);
        // Get references to each of the radio buttons
        mChoice1 = (RadioButton) findViewById(R.id.multiple_choice_1);
        mChoice2 = (RadioButton) findViewById(R.id.multiple_choice_2);
        mChoice3 = (RadioButton) findViewById(R.id.multiple_choice_3);
        mChoice4 = (RadioButton) findViewById(R.id.multiple_choice_4);

        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Query the radio button group to find out which radio button
                // was selected. Store the id of the selected one in the
                // variable selectedAnswerId.
                int selectedAnswerId = mQuestionGroup.getCheckedRadioButtonId();

                // Pass the id of the selected radio button into the checkAnswer
                // method. The checkAnswer handles toasting whether it is correct or not.
                checkAnswer(selectedAnswerId);
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

        // Call the updateQuestion method to move to the next question
        updateQuestion();

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
        // Fetch the question choice text from the question object
        int[] choices = mQuestionBank[mCurrentIndex].getChoiceResIds();
        // Assign each question choice text to the text property of the radio button.
        mChoice1.setText(choices[0]);
        mChoice2.setText(choices[1]);
        mChoice3.setText(choices[2]);
        mChoice4.setText(choices[3]);
    }

    private void checkAnswer(int selectedRadioButtonId) {

        // Get the correct answer res id from the question object
        int correctAnswer = mQuestionBank[mCurrentIndex].getCorrectAnswerResId();

        // Initialize the message res id to zero. Will be overwritten below.
        int messageResId = 0;

        // Check to see if the selected Radio button id matches the correct answer.
        if (selectedRadioButtonId == correctAnswer) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        // Toast out the result
        Toast.makeText(
                this,
                messageResId,
                Toast.LENGTH_SHORT
        ).show();
    }
}
