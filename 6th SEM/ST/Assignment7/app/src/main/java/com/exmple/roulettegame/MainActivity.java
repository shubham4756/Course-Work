package com.exmple.roulettegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText amountEt;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEt = findViewById(R.id.amountEt);
        startBtn = findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !TextUtils.isEmpty(amountEt.getText()) && TextUtils.isDigitsOnly(amountEt.getText()) ) {
                    int amount = Integer.parseInt(String.valueOf(amountEt.getText()));
                    Intent intent = new Intent(getBaseContext(),GameActivity.class);
                    intent.putExtra("amount", amount);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Enter valid amount", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}