package com.exmple.roulettegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView amountTv = findViewById(R.id.summaryAmount);
        TextView rewardTv = findViewById(R.id.summaryReward);
        TextView gainTv = findViewById(R.id.summaryGain);

        Button playAgainBtn = findViewById(R.id.playAgain);

        int amount = getIntent().getIntExtra("amount", 0);
        int reward = getIntent().getIntExtra("reward", 0);

        int gain = reward - (amount/100) *100;

        amountTv.setText("Total amount invested is " + amount);
        rewardTv.setText("Total reward amount is " + reward);

        if(gain < 0) {
            // loss
            gain *= -1;
            gainTv.setText("Total Loss " + gain);
            Toast.makeText(SummaryActivity.this, "Better Luck Next time", Toast.LENGTH_LONG).show();
        } else {
            // win
            gainTv.setText("Total Profit " + gain);
            Toast.makeText(SummaryActivity.this, "Congratulation", Toast.LENGTH_LONG).show();
        }

        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}