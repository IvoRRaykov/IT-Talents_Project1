package com.example.preshlen.sologamelonesurvivour.front;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class AnswerQuestionActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int MIN_TIME_TO_THINK = 2000;
    public static final int MAX_TIME_TO_THINK = 6000;


    private Question question;

    private TextView questionTv;
    private TextView firstAnswer;
    private TextView secondAnswer;
    private TextView thirdAnswer;
    private TextView fourthAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

        questionTv = (TextView) findViewById(R.id.question);
        firstAnswer = (TextView) findViewById(R.id.first_answer);
        secondAnswer = (TextView) findViewById(R.id.second_answer);
        thirdAnswer = (TextView) findViewById(R.id.third_answer);
        fourthAnswer = (TextView) findViewById(R.id.fourth_answer);
        Bundle extras = getIntent().getExtras();
        question = (Question) extras.getSerializable("question");
        this.questionTv.setText(question.getText());
        this.firstAnswer.setText(question.getAnswers().get(0).getText());
        this.secondAnswer.setText(question.getAnswers().get(1).getText());
        this.thirdAnswer.setText(question.getAnswers().get(2).getText());
        this.fourthAnswer.setText(question.getAnswers().get(3).getText());

        if (!MapActivity.isMyTurn()) {
            Toast.makeText(getApplicationContext(), "bot chose answer 1", Toast.LENGTH_SHORT).show();

            Intent returnIntent = new Intent();
            boolean rightAnswer = question.getRightAnswer().getText().equals(randomAnswer().getText().toString());
            returnIntent.putExtra("rightAnswer", rightAnswer);
            setResult(Activity.RESULT_OK, returnIntent);

            int thinkingTime = MIN_TIME_TO_THINK + (int)(Math.random() * ((MAX_TIME_TO_THINK - MIN_TIME_TO_THINK) + 1));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, thinkingTime);
        } else {
            Toast.makeText(getApplicationContext(), " MY TURN", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int answerIndex = 0;
        switch (v.getId()) {
            case R.id.first_answer:
                answerIndex = 0;
                break;
            case R.id.second_answer:
                answerIndex = 1;
                break;
            case R.id.third_answer:
                answerIndex = 2;
                break;
            case R.id.fourth_answer:
                answerIndex = 3;
                break;
        }
        String answer = question.getAnswers().get(answerIndex).getText();
        Intent returnIntent = new Intent();
        boolean rightAnswer = question.getRightAnswer().getText().equals(answer);
        returnIntent.putExtra("rightAnswer", rightAnswer);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    TextView randomAnswer(){
        int rand = 1 + (int)(Math.random() * ((4 - 1) + 1));
        switch (rand){
            case 1:
                return this.firstAnswer;
            case 2:
                return this.secondAnswer;
            case 3:
                return this.thirdAnswer;
            case 4:
                return this.fourthAnswer;

        }
        return null;
    }
}
