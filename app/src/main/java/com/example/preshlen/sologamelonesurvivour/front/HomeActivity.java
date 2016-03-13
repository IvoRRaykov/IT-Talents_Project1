package com.example.preshlen.sologamelonesurvivour.front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.managers.QuestionManager;
import com.example.preshlen.sologamelonesurvivour.model.User;
import com.example.preshlen.sologamelonesurvivour.model.managers.UserManager;

public class HomeActivity extends AppCompatActivity {
    QuestionManager qm = QuestionManager.getInstance(this);
    User player = UserManager.getPlayer();

    Button startButton;
    Button buildDeckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//----------
        qm.createQuestionPackFroUser(player);
//----------
        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MapActivity.class));
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
