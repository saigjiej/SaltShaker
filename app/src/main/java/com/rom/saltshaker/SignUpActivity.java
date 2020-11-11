package com.rom.saltshaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUp";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.sign_up_btn).setOnClickListener(onClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.sign_up_btn:
                    Log.e("클릭", "회원가입");
                    signUp();
                    break;
            }
        }
    };

    private void signUp(){
        String email = ((EditText)findViewById(R.id.sign_up_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.sign_up_pwd)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.sign_up_pwdcheck)).getText().toString();
        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){ // 이메일과 패스워드가 적혀져있지 않을 경우
            if(password.equals(passwordCheck)){ // 패스워드와 패스워드 확인이 같을 경우
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) { // 성공 시
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(SignUpActivity.this, "회원가입에 성공하셨습니다!", Toast.LENGTH_LONG).show();
                                    startLoginActivity(); // 로그인 창으로 이동
                                } else { // 실패 시
                                    if(task.getException() != null){
                                        Toast.makeText(SignUpActivity.this, "회원가입을 다시 시도해주세요", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });

            } else { // 패스워드와 패스워드 확인이 다를 경우
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }

        } else { // 이메일과 비밀번호를 입력하지 않았을 경우
            Toast.makeText(SignUpActivity.this, "이메일 또는 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show();
        }
    }


    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}