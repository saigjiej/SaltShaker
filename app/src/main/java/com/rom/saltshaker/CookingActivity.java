package com.rom.saltshaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

public class CookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

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
}