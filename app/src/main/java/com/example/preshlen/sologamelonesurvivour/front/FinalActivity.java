package com.example.preshlen.sologamelonesurvivour.front;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.preshlen.sologamelonesurvivour.R;

import org.w3c.dom.Text;

public class FinalActivity extends AppCompatActivity {
    Button restart;
    Button quit;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Bundle extras = getIntent().getExtras();
        String msg = extras.getString("msg");
        text = (TextView) findViewById(R.id.text);
        text.setText(msg);

        quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalActivity.this, LoginActivity.class));
            }
        });

    }
    public void onBackPressed() {

    }

}
