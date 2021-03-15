package com.rom.saltshaker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //툴바 사용
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //툴바 뒤로가기 생성(뒤로가기 화살표가 자동 제공됨)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //뒤로가기 버튼 설정(drawable에 이미지를 등록해서 화살표 대신 사용가능, 사이즈가 충분히 작아야 함)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        //툴바 배경색
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C6D6F7")));

        ImageButton movieButton = findViewById(R.id.movie_button);
        ImageButton bookButton = findViewById(R.id.book_button);
        ImageButton accessoriesButton = findViewById(R.id.accessories_button);
        ImageButton plantingButton = findViewById(R.id.planting_button);
        ImageButton cookingButton = findViewById(R.id.cooking_button);
        ImageButton bakingButton = findViewById(R.id.baking_button);

        movieButton.setOnClickListener(imgBtnHandler);
        bookButton.setOnClickListener(imgBtnHandler);
        accessoriesButton.setOnClickListener(imgBtnHandler);
        plantingButton.setOnClickListener(imgBtnHandler);
        cookingButton.setOnClickListener(imgBtnHandler);
        bakingButton.setOnClickListener(imgBtnHandler);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:{ //툴바 뒤로가기 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener imgBtnHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.movie_button:
                    intent = new Intent(getApplicationContext(), MovieActivity.class);
                    break;
                case R.id.book_button:
                    intent = new Intent(getApplicationContext(), BookActivity.class);
                    break;
                case R.id.accessories_button:
                    intent = new Intent(getApplicationContext(), AccessoriesActivity.class);
                    break;
                case R.id.planting_button:
                    intent = new Intent(getApplicationContext(), PlantingActivity.class);
                    break;
                case R.id.cooking_button:
                    intent = new Intent(getApplicationContext(), CookingActivity.class);
                    break;
                case R.id.baking_button:
                    intent = new Intent(getApplicationContext(), BakingActivity.class);
                    break;
            }
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    };
}