package com.ssb.app0627;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //MainActivity에서 전달한 데이터 가져오기
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");


        Button mainbtn =(Button)findViewById(R.id.mainbtn);
        mainbtn.setOnClickListener((view)->{
           //상위 Activity에게 전달할 데이터 만들기
            Intent intent1 = new Intent();
            intent1.putExtra("data","백범 김구");
            //데이터 전송 : 1번 코드로 intent1  전송
            setResult(1,intent1);

            finish();
        });

        Toast.makeText(SubActivity.this,message,Toast.LENGTH_LONG).show();

    }
}
