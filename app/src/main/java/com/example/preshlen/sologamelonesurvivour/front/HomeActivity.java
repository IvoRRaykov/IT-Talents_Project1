package com.example.preshlen.sologamelonesurvivour.front;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.fragments.BuildDeckFragment;
import com.example.preshlen.sologamelonesurvivour.model.managers.QuestionManager;
import com.example.preshlen.sologamelonesurvivour.model.managers.UserManager;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    QuestionManager qm = QuestionManager.getInstance(this);

    Button startButton;
    Button buildDeckButton;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//----------
        qm.createQuestionPackFroUser(UserManager.getPlayer());
//----------

        rl = (RelativeLayout) findViewById(R.id.info_layout);
        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        startButton.setVisibility(View.GONE);
        rl.setVisibility(View.GONE);

        buildDeckButton = (Button) findViewById(R.id.build_deck_button);
        buildDeckButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                startActivity(new Intent(HomeActivity.this, MapActivity.class));
                break;
            case R.id.build_deck_button:
                openBuildDeckFragment();
                break;
        }
    }

    private void openBuildDeckFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BuildDeckFragment fragment = new BuildDeckFragment();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(" asd");
        fragmentTransaction.commit();
        buildDeckButton.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() > 0) {
            if(!UserManager.isDeckFull()){
                buildDeckButton.setVisibility(View.VISIBLE);
            }
            startButton.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
        }
        super.onBackPressed();
    }
}
