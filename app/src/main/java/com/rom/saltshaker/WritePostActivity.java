package com.rom.saltshaker;

import android.content.Intent;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class WritePostActivity extends AppCompatActivity {
    private static final String TAG = "WritePostActivity";
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        findViewById(R.id.check_btn).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.check_btn:
                    profileUpdate();
                    myStartActivity(MainActivity.class);
                    break;

            }
        }
    };



    // 추억 내용 글쓰기
    private void profileUpdate() {
        final String title= ((EditText) findViewById(R.id.title_edt)).getText().toString();
        final String contents = ((EditText) findViewById(R.id.contents_edt)).getText().toString();

        if (title.length() > 0 ) { // 제목이 0보다 클 경우


            // final ArrayList<String> contentsList = new ArrayList<>();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            // WriteInfo
            PostInfo postInfo = new PostInfo(title, contents, user.getUid(), "test"); // 입력 받음
            uploader(postInfo);

        } else {
            Toast.makeText(WritePostActivity.this, "글을 작성해주세요", Toast.LENGTH_LONG).show();
        }

    }

    private void uploader(PostInfo postInfo) {  // 파이어베이스 업로드
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posts").add(postInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {  // 성공했을 경우, 회원 아이디 출력
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) { // 실패했을 경우, 에러메세지 출력
                        Log.d(TAG, "Error adding document", e);
                    }
                });

    }

    private void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 앱이 꺼짐
        startActivity(intent);
    }
}