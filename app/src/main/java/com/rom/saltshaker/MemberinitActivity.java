package com.rom.saltshaker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

// 회원 정보 입력
public class MemberinitActivity extends AppCompatActivity {
    private static final String TAG = "MemberinitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_init);

        findViewById(R.id.confirm_btn).setOnClickListener(onClickListener);
    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.confirm_btn:
                    Log.e("클릭", "닉네임 등록");
                    profileUpdate();
                    break;
            }
        }
    };

    // 이름 등록 (회원정보)
    private void profileUpdate() {
        String name = ((EditText) findViewById(R.id.nickname_input)).getText().toString();
        if (name.length() > 0) { // 닉네임이 0보다 큰 경우
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // 고유키를 위하여 (사용자마다 UI키 선정)
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            MemberInfo memberInfo = new MemberInfo(name);

            if (user != null) { // 닉네임이 입력됐을 경우
                db.collection("users").document(user.getUid()).set(memberInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() { // 성공했을 경우
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MemberinitActivity.this, "회원정보 등록을 성공하였습니다!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() { // 실패했을 경우
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MemberinitActivity.this, "회원정보 등록에 실패하였습니다.", Toast.LENGTH_LONG).show();
                                Log.w(TAG, "Error writing document", e);
                            }
                        });

            } else {
                Toast.makeText(MemberinitActivity.this, "회원정보를 입력해주세요", Toast.LENGTH_LONG).show();
            }
        }

    }


}