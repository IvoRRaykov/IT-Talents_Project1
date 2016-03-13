package com.example.preshlen.sologamelonesurvivour.front;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.classes.Question;
import com.example.preshlen.sologamelonesurvivour.model.classes.Zone;
import com.example.preshlen.sologamelonesurvivour.model.managers.UserManager;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView zoneOne;
    private ImageView zoneFive;

    private static boolean isMyTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        zoneOne = (ImageView) findViewById(R.id.zone_one);
        zoneOne.setOnClickListener(this);
        zoneFive = (ImageView) findViewById(R.id.zone_five);
        zoneFive.setOnClickListener(this);
        isMyTurn = false;
        Log.e("tag", isMyTurn+ "");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ChooseQuestionActivity.class);
        int questionsToAsk  = 0;
        switch (v.getId()) {
            case R.id.zone_one:
                questionsToAsk = 1;
                break;
            case R.id.zone_five:
                questionsToAsk = 5;
                break;
        }
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            isMyTurn = !isMyTurn;
            Log.e("tag", isMyTurn+ "");
        }
    }

    @Override
    public void onBackPressed() {

    }

    public static boolean isMyTurn() {
        return isMyTurn;
    }

    public static void setIsMyTurn(boolean isMyTurn) {
        MapActivity.isMyTurn = isMyTurn;
    }
}
