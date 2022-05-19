package com.exmple.roulettegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final String[] SECTORS = { "32 red", "15 black",
            "19 red", "4 black", "21 red", "2 black", "25 red", "17 black", "34 red",
            "6 black", "27 red","13 black", "36 red", "11 black", "30 red", "8 black",
            "23 red", "10 black", "5 red", "24 black", "16 red", "33 black",
            "1 red", "20 black", "14 red", "31 black", "9 red", "22 black",
            "18 red", "29 black", "7 red", "28 black", "12 red", "35 black",
            "3 red", "26 black", "zero"
    };

    private final String[] CHOICES = { "Odd No. Reward ₹ 100", "Even no. Reward ₹ 100"
            , "Prime no. Reward ₹ 500", "Choose No. Reward ₹ 5000"
    };

    private static final int[] NUMBERS = {32, 15, 19, 4, 21, 2, 25, 17, 34,6,27,13,36,11,30,8,
            23,10,5,24,16,33,1,20,14,31,9,22,18,29,7,28,12,35,3,26,0};

    private static HashSet<Integer> PRIMES = new HashSet<Integer>(Arrays.asList(2,3,5,7,11,13,17,19,23,29,31));;

    private static final Random RANDOM = new Random();
    private int degree = 0, degreeOld = 0;
    private static final float HALF_SECTOR = (360.0f/37.0f)/2.0f;

    private Button spinBtn;
    private TextView resultTv;
    private ImageView wheel;

    private TextView trialNumberTv, remainingAmountTv, trialLeftTv, rewardEarnedTv;

    private NumberPicker numberPicker;
    private Spinner spinner;

    private int amount = 0, type = 1, choose = -1;
    private int totalTrial = 0, trialLeft = 0;
    private int rewardEarned = 0, remainingAmount = 0;
    private int trialNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        amount = getIntent().getIntExtra("amount", 0);
//        Toast.makeText(GameActivity.this, "" + amount, Toast.LENGTH_LONG).show();

        trialLeft = totalTrial = amount / 100;
        remainingAmount = amount;


        numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(36);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,CHOICES);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        makePickerInvisible();
                        type = 1;
                        choose = -1;
                        break;
                    case 1:
                        makePickerInvisible();
                        type = 2;
                        choose = -1;
                        break;
                    case 2:
                        makePickerInvisible();
                        type = 3;
                        choose = -1;
                        break;
                    default:
                        makePickerVisible();
                        type = 4;
                        choose = numberPicker.getValue();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinBtn = findViewById(R.id.spinBtn);
        resultTv = findViewById(R.id.resultTv);
        wheel = findViewById(R.id.wheel);

        trialNumberTv = findViewById(R.id.trialNumber);
        remainingAmountTv = findViewById(R.id.remainingAmount);
        trialLeftTv = findViewById(R.id.trialLeft);
        rewardEarnedTv = findViewById(R.id.rewardEarned);

        trialNumberTv.setText("Trial Number : " + trialNumber);
        remainingAmountTv.setText("Remaining Amount : " + remainingAmount);
        trialLeftTv.setText("Trial Left : " + trialLeft);
        rewardEarnedTv.setText("Reward Earned : " + rewardEarned);

        if(trialLeft == 0) {
            spinBtn.setText("See Summary");
        }

        spinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(trialLeft == 0) {
                    Toast.makeText(GameActivity.this, "All trial are finished", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GameActivity.this, SummaryActivity.class);
                    intent.putExtra("amount", amount);
                    intent.putExtra("reward", rewardEarned);
                    startActivity(intent);
                } else {
                    if(type == 4) {
                        choose = numberPicker.getValue();
                    }
                    spin(view);
                }
            }
        });
    }

    public void spin(View v) {
        degreeOld = degree % 360;
        degree = RANDOM.nextInt(360) + 720;

        RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());
        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                resultTv.setText("");
                trialNumberTv.setText("");
                remainingAmountTv.setText("");
                trialLeftTv.setText("");
                rewardEarnedTv.setText("");

                spinner.setVisibility(View.GONE);
                numberPicker.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int i = getSector(360 - (degree % 360));
                resultTv.setText(SECTORS[i]);
                trialNumber++;
                trialLeft--;
                remainingAmount-=100;

                int tempReward = 0;

                if(type == 1) {
                    if(isOdd(i)) {
                        rewardEarned += 100;
                        tempReward = 100;
                    }
                } else if (type == 2) {
                    if(isEven(i)) {
                        rewardEarned += 100;
                        tempReward = 100;
                    }
                } else if (type == 3) {
                    if(isPrime(i)) {
                        rewardEarned += 500;
                        tempReward = 500;
                    }
                } else {
                    if(isSame(i)) {
                        rewardEarned += 5000;
                        tempReward = 5000;
                    }
                }


                trialNumberTv.setText("Trial Number : " + trialNumber);
                remainingAmountTv.setText("Remaining Amount : " + remainingAmount);
                trialLeftTv.setText("Trial Left : " + trialLeft);
                rewardEarnedTv.setText("Reward Earned : " + tempReward);

                if(trialLeft == 0) {
                    spinBtn.setText("See Summary");
                }


                spinner.setVisibility(View.VISIBLE);
                if(type == 4)
                    numberPicker.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        wheel.startAnimation(rotateAnim);
    }

    private int getSector(int degrees) {
        int i = 0;
        String text = null;

        do {
            float start = HALF_SECTOR * (i * 2 + 1);
            float end = HALF_SECTOR * (i * 2 + 3);

            if (degrees >= start && degrees < end) {
                text = SECTORS[i];
//                Toast.makeText(GameActivity.this,SECTORS[i],Toast.LENGTH_LONG).show();
                break;
            }

            i++;
        } while (text == null  &&  i < SECTORS.length);

        if(i>=36 || text==null) {
            i = 36;
        }

        return i;
    }

    private boolean isEven(int i) {
        return NUMBERS[i] %2 == 0;
    }

    private boolean isOdd(int i) {
        return NUMBERS[i]%2 == 1;
    }

    private boolean isPrime(int i) {
        return PRIMES.contains(NUMBERS[i]);
    }

    private boolean isSame(int i) {
        return choose == NUMBERS[i];
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    public void makePickerInvisible() {
        numberPicker.setVisibility(View.GONE);
    }

    public void makePickerVisible() {
        numberPicker.setVisibility(View.VISIBLE);
    }
}