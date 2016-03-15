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
import android.widget.TextView;
import android.widget.Toast;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.example.preshlen.sologamelonesurvivour.model.managers.QuestionManager;
import com.example.preshlen.sologamelonesurvivour.model.managers.UserManager;

import org.w3c.dom.Text;

import java.util.HashMap;

public class ChooseQuestionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
   private int questionsToAsk = 0;
    private int zoneID = 0;

    QuestionManager qm = QuestionManager.getInstance(this);
    ListView questionsList;
    ArrayAdapter adapter;
    TextView leftAttacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_question);
        leftAttacks = (TextView) findViewById(R.id.left_attacks);

        Bundle extras = getIntent().getExtras();
        questionsToAsk = extras.getInt("atacks");
        zoneID = extras.getInt("zoneID");

        leftAttacks.setText(String.valueOf(questionsToAsk));
        questionsList = (ListView) findViewById(R.id.questions_list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter.addAll(UserManager.getDeck());
        questionsList.setAdapter(adapter);
        questionsList.setOnItemClickListener(this);

        if (MapActivity.isMyTurn()) {
            botChoseQuestion();

        } else {

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, AnswerQuestionActivity.class);
        Question question = UserManager.getQuestionFromDeck(((String) adapter.getItem(position)));
        adapter.remove(adapter.getItem(position));
        adapter.notifyDataSetChanged();
        intent.putExtra("question", question);
        this.questionsToAsk--;
        leftAttacks.setText(String.valueOf(questionsToAsk));
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("zoneIDReturned", zoneID);

            if (data.getExtras().getBoolean("rightAnswer")) {
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                return;
            } else {
                if (this.questionsToAsk == 0) {
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                    return;
                }
            }
        }

        if (MapActivity.isMyTurn()) {
            botChoseQuestion();
        }
    }

    void botChoseQuestion() {
        HashMap<Integer, Question> questions = qm.getEnemyQuestions();
        int random = 0 + (int)(Math.random() * (((questions.size()-1) - 0) + 1));
        qm.removeQuestionFromEnemiesQuestions(questions.get(random));
        this.questionsToAsk--;

        Intent intent = new Intent(this, AnswerQuestionActivity.class);
        intent.putExtra("question", questions.get(random));
        startActivityForResult(intent, 2);
    }

    public void onBackPressed() {

    }
}
