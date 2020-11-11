package com.rom.saltshaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ImageButton homeActivity = findViewById(R.id.homeActivity);
        ImageButton memory = findViewById(R.id.memory);

        homeActivity.setOnClickListener(imgBtnHandler);
        memory.setOnClickListener(imgBtnHandler);
    }

    View.OnClickListener imgBtnHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.homeActivity:
                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                    break;
                case R.id.memory:
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    break;
            }
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    };
}