package com.example.preshlen.sologamelonesurvivour.front;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.Question;
import com.example.preshlen.sologamelonesurvivour.model.QuestionManager;
import com.example.preshlen.sologamelonesurvivour.model.User;
import com.example.preshlen.sologamelonesurvivour.model.UserManager;

import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    QuestionManager qm = QuestionManager.getInstance(this);
    User player = UserManager.getPlayer();

    Button startButton;
    Button buildDeckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        qm.createQuestionPackFroUser(player);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder("");
                for (Map.Entry<Integer, Question> entry : player.getAllQuestions().entrySet()) {
                    Log.e("da" , "ne");
                    sb.append(entry.getValue().getText());
                    sb.append(" ");
                }


                startButton.setText(sb.toString());

            }
        });

        buildDeckButton = (Button) findViewById(R.id.build_deck_button);
        buildDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
