package com.example.preshlen.sologamelonesurvivour.front;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.example.preshlen.sologamelonesurvivour.model.managers.UserManager;

public class ChooseQuestionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    int questionsToAsk =0;

    ListView questionsList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_question);

        Bundle extras = getIntent().getExtras();
        questionsToAsk = extras.getInt("atacks");

        questionsList = (ListView) findViewById(R.id.questions_list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter.addAll(UserManager.getDeck());
        questionsList.setAdapter(adapter);
        questionsList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, AnswerQuestionActivity.class);
        Question question = UserManager.getQuestionFromDeck(((String) adapter.getItem(position)));
        intent.putExtra("question", question);

        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if(data.getExtras().getBoolean("rightAnswer")) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
            else{
                this.questionsToAsk--;
                if(this.questionsToAsk ==0){
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }

        }
    }
}
