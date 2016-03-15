package com.example.preshlen.sologamelonesurvivour.front;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.enums.DominationEnum;
import com.example.preshlen.sologamelonesurvivour.model.managers.UserManager;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    Handler myHandler = new Handler();
    String systemMsg = "";

    private TextView playerPoints;
    private TextView enemyPoints;
    private ImageView zoneOne;
    private ImageView zoneTwo;
    private ImageView zoneThree;
    private ImageView zoneFour;
    private ImageView zoneFive;
    private ImageView zoneOneOther;
    private ImageView zoneTwoOther;
    private ImageView zoneThreeOther;
    private ImageView zoneFourOther;
    private ImageView zoneFiveOther;
    private ImageView[] images;

    private static boolean isMyTurn;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        playerPoints = (TextView) findViewById(R.id.player_points);
        enemyPoints = (TextView) findViewById(R.id.enemy_points);

        zoneOne = (ImageView) findViewById(R.id.zone_one);
        zoneOne.setOnClickListener(this);
        zoneTwo = (ImageView) findViewById(R.id.zone_two);
        zoneTwo.setOnClickListener(this);
        zoneThree = (ImageView) findViewById(R.id.zone_three);
        zoneThree.setOnClickListener(this);
        zoneFour = (ImageView) findViewById(R.id.zone_four);
        zoneFour.setOnClickListener(this);
        zoneFive = (ImageView) findViewById(R.id.zone_five);
        zoneFive.setOnClickListener(this);
        zoneOneOther = (ImageView) findViewById(R.id.zone_one_other);
        zoneOneOther.setOnClickListener(this);
        zoneTwoOther = (ImageView) findViewById(R.id.zone_two_other);
        zoneTwoOther.setOnClickListener(this);
        zoneThreeOther = (ImageView) findViewById(R.id.zone_three_other);
        zoneThreeOther.setOnClickListener(this);
        zoneFourOther = (ImageView) findViewById(R.id.zone_four_other);
        zoneFourOther.setOnClickListener(this);
        zoneFiveOther = (ImageView) findViewById(R.id.zone_five_other);
        zoneFiveOther.setOnClickListener(this);
        isMyTurn = false;
        images = new ImageView[]{zoneOne, zoneTwo, zoneThree, zoneFour,
                zoneFive, zoneOneOther, zoneTwoOther, zoneThreeOther, zoneFourOther, zoneFiveOther};
    }

    @Override
    public void onClick(View v) {
        if (!isColorBlue((ImageView) v)) {
            final Intent intent = new Intent(this, ChooseQuestionActivity.class);
            int zoneID = 0;
            atackCounts(v.getId());
            v.setBackgroundResource(R.drawable.dark_blue);
            zoneID = v.getId();
            intent.putExtra("atacks", atackCounts(zoneID));
            intent.putExtra("zoneID", zoneID);

            myHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivityForResult(intent, 1);
                }
            }, 2000);

        } else {
            Toast.makeText(MapActivity.this, "gofuckyourself", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//-----------------

        Bundle extras = data.getExtras();
        int zoneID = extras.getInt("zoneIDReturned");
        ImageView zone = (ImageView) findViewById(zoneID);
        assert zone != null;
        if (resultCode == Activity.RESULT_OK) {
            int points = calculatePointsByZoneID(zoneID);
            if (!isMyTurn) {
                UserManager.addPlayerPoints(points);
                playerPoints.setText(String.valueOf(Integer.valueOf(playerPoints.getText().toString()) + points));
                zone.setBackgroundResource(R.drawable.blue);
            } else {
                UserManager.addEnemyPoints(points);
                enemyPoints.setText(String.valueOf(Integer.valueOf(enemyPoints.getText().toString()) + points));
                zone.setBackgroundResource(R.drawable.red);
            }
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            if (!isMyTurn) {
                zone.setBackgroundResource(R.drawable.red);
            } else {
                zone.setBackgroundResource(R.drawable.blue);
            }

        }
        checkIfFinish();

        isMyTurn = !isMyTurn;
        if (isMyTurn) {
            Toast.makeText(MapActivity.this, "My Turn To Defend Now!!!", Toast.LENGTH_SHORT).show();
            int randomZoneID = choseRandomZone();
            findViewById(randomZoneID).setBackgroundResource(R.drawable.dark_red);
            final Intent intent = new Intent(this, ChooseQuestionActivity.class);
            intent.putExtra("atacks", atackCounts(randomZoneID));
            intent.putExtra("zoneID", randomZoneID);


            myHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivityForResult(intent, 1);
                }
            }, 3000);
        }

    }

    private void checkIfFinish() {
        Intent intentA = new Intent(this, FinalActivity.class);
        if (isThereFullDomination().equals(DominationEnum.YOU_WIN)) {
            intentA.putExtra("msg", "You win !?!?!? Who could belive it !? ");
            startActivity(intentA);
        } else if (isThereFullDomination().equals(DominationEnum.YOU_LOSE)) {
            intentA.putExtra("msg", "You lose :(:(:(:( Not a big surpirse, though... ");
            startActivity(intentA);
        }
    }


    private DominationEnum isThereFullDomination() {
        int blueCounter = 0;
        for (ImageView image : images) {
            if (isColorBlue(image)) blueCounter++;
        }
        if (blueCounter == 0 || UserManager.getEnemysPoints() >= UserManager.MAX_POINTS || UserManager.getPlayer().getDeck().size() == 0) {
            return DominationEnum.YOU_LOSE;
        } else if (blueCounter == images.length - 1 || UserManager.getPlayersPoints() >= UserManager.MAX_POINTS) {
            return DominationEnum.YOU_WIN;
        }
        return DominationEnum.NO_FULL_DOMINATION;
    }


    int atackCounts(int zoneID) {
        int questionsToAsk = 0;
        switch (zoneID) {
            case R.id.zone_one: // doni ima zasluga
            case R.id.zone_one_other:
            case R.id.zone_two:
            case R.id.zone_two_other:
                questionsToAsk = 1;
                break;
            case R.id.zone_three:
            case R.id.zone_three_other:
            case R.id.zone_four:
            case R.id.zone_four_other:
                questionsToAsk = 2;
                break;
            case R.id.zone_five:
            case R.id.zone_five_other:
                questionsToAsk = 3;
                break;
        }
        return questionsToAsk;
    }

    private int choseRandomZone() {
        int random;
        while (true) {
            random = 0 + (int) (Math.random() * (((images.length - 1) - 0) + 1));
            if (isColorBlue(images[random])) {
                return images[random].getId();
            }
        }
    }

    private boolean isColorBlue(ImageView view) {
        return view.getBackground().getConstantState().equals
                (getResources().getDrawable(R.drawable.blue).getConstantState()) ||
                view.getBackground().getConstantState().equals
                        (getResources().getDrawable(R.drawable.dark_blue).getConstantState());
    }

    private int calculatePointsByZoneID(int zoneID) {
        int pointsToAdd = 0;
        switch (zoneID) {
            case R.id.zone_one: // doni ima zasluga
            case R.id.zone_one_other:
            case R.id.zone_two:
            case R.id.zone_two_other:
                pointsToAdd = 100;
                break;
            case R.id.zone_three:
            case R.id.zone_three_other:
            case R.id.zone_four:
            case R.id.zone_four_other:
                pointsToAdd = 250;
                break;
            case R.id.zone_five:
            case R.id.zone_five_other:
                pointsToAdd = 400;
                break;
        }
        return pointsToAdd;
    }


    public void onBackPressed() {

    }

    public static boolean isMyTurn() {
        return isMyTurn;
    }

    public void startFinishingFragment(String msg) {

    }


}
