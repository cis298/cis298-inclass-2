package edu.kvcc.cis298.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    // Each instance of question will store the integer from
    // the R file for the specific string. It is not storing
    // the questions string as a string, but instead the resource
    // identifier for the string in strings.xml and R.
    public Question(int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
