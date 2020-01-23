package edu.kvcc.cis298.geoquiz;

public class Question {
    // Holds the question text
    private int mTextResId;
    // Integer to represent the integer id of the correct resource
    private int mCorrectAnswerResId;
    // Integer array to hold the resource ids of the choices for the question
    private int[] mChoiceResIds;

    // Each instance of question will store the integer from
    // the R file for the specific string. It is not storing
    // the questions string as a string, but instead the resource
    // identifier for the string in strings.xml and R.
    public Question(int textResId, int correctAnswerResId, int[] choiceResIds){
        mTextResId = textResId;
        mCorrectAnswerResId = correctAnswerResId;
        mChoiceResIds = choiceResIds;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public int getCorrectAnswerResId() {
        return mCorrectAnswerResId;
    }

    public void setCorrectAnswerResId(int correctAnswerResId) {
        mCorrectAnswerResId = correctAnswerResId;
    }

    public int[] getChoiceResIds() {
        return mChoiceResIds;
    }

    public void setChoiceResIds(int[] choiceResIds) {
        mChoiceResIds = choiceResIds;
    }
}
