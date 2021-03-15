package com.rom.saltshaker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "SignUp";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.sign_up_btn).setOnClickListener(onClickListener);
        findViewById(R.id.sign_in_btn).setOnClickListener(onClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.sign_up_btn:
                    Log.e("클릭", "회원가입으로 이동");
                    startLoginActivity(); // 로그인 창으로 이동
                    break;
                case R.id.sign_in_btn:
                    Log.e("클릭", "로그인");
                    signIn();
                    break;

            }
        }
    };

    private void signIn() {
        String email = ((EditText) findViewById(R.id.sign_up_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.sign_up_pwd)).getText().toString();

        if (email.length() > 0 && password.length() > 0) { // 이메일과 패스워드가 적혀져있지 않을 경우
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) { // 성공 시
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Salt Shaker에 오신 것을 환영합니다!", Toast.LENGTH_LONG).show();
                                startUserActivity(); // 회원 activity로 이동
                            } else { // 실패 시
                                if (task.getException() != null) {
                                    Toast.makeText(LoginActivity.this, "로그인을 다시 시도해주세요", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });

        } else { // 이메일과 비밀번호를 입력하지 않았을 경우
            Toast.makeText(LoginActivity.this, "이메일 또는 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show();
        }
    }


    private void startLoginActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void startUserActivity(){
        Intent intent = new Intent(this, ChooseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 앱이 꺼짐
        startActivity(intent);
    }

}