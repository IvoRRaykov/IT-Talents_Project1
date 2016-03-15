package com.example.preshlen.sologamelonesurvivour.front;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.example.preshlen.sologamelonesurvivour.model.managers.QuestionManager;
import com.example.preshlen.sologamelonesurvivour.model.managers.UserManager;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class AnswerQuestionActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int MIN_TIME_TO_THINK = 8000;
    public static final int MAX_TIME_TO_THINK = 12000;
    Handler myHandler = new Handler();

    private Question question = null;

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
        firstAnswer.setOnClickListener(this);
        secondAnswer = (TextView) findViewById(R.id.second_answer);
        secondAnswer.setOnClickListener(this);
        thirdAnswer = (TextView) findViewById(R.id.third_answer);
        thirdAnswer.setOnClickListener(this);
        fourthAnswer = (TextView) findViewById(R.id.fourth_answer);
        fourthAnswer.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        question = (Question) extras.getSerializable("question");
        this.questionTv.setText(question.getText());
        this.firstAnswer.setText(question.getAnswers().get(0).getText());
        this.secondAnswer.setText(question.getAnswers().get(1).getText());
        this.thirdAnswer.setText(question.getAnswers().get(2).getText());
        this.fourthAnswer.setText(question.getAnswers().get(3).getText());

        if (!MapActivity.isMyTurn()) {

            Intent returnIntent = new Intent();
            TextView botChoice = randomAnswer();
            boolean rightAnswer = question.getRightAnswer().getText().equals(botChoice.getText().toString());
            if(rightAnswer){
                botChoice.setBackgroundColor(Color.GREEN);
            }
            else{
                botChoice.setBackgroundColor(Color.RED);
            }
            returnIntent.putExtra("rightAnswer", rightAnswer);
            setResult(Activity.RESULT_OK, returnIntent);
            String toast = (rightAnswer)? "bot chose: " + botChoice.getText().toString() + " and is RIGHT!" : "bot chose: " + botChoice.getText().toString() + " and is WRONG!";
            Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
            myHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, MIN_TIME_TO_THINK);
        } else {

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
        final Intent returnIntent = new Intent();
        final boolean rightAnswer = question.getRightAnswer().getText().equals(answer);
        if(rightAnswer){
            v.setBackgroundColor(Color.GREEN);
        }
        else{
            v.setBackgroundColor(Color.RED);
        }
        returnIntent.putExtra("rightAnswer", rightAnswer);
        setResult(Activity.RESULT_OK, returnIntent);
        if (rightAnswer) {
            QuestionManager.getInstance(AnswerQuestionActivity.this).addQuestoinToPlayersDatabase(UserManager.getPlayer(), question);
            Toast.makeText(AnswerQuestionActivity.this, "GG, you won that question ! Cheer Up!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AnswerQuestionActivity.this, "Sorry kiddo, wrong answer :((", Toast.LENGTH_SHORT).show();
        }
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                finish();
            }
        }, MIN_TIME_TO_THINK);


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
