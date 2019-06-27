package com.ssb.app0627;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NationActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation);

        Button callphone = (Button)findViewById(R.id.callphone);
        callphone.setOnClickListener((view)->{
            //실시간 권한 체크 (6.0 이상부터)
            if(ContextCompat.checkSelfPermission(NationActivity.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-9497-8523"));
                startActivity(intent);
            }
        });

        listView =(ListView)findViewById(R.id.nationlist);

        //데이터베이스 사용을 위한 객체를 생성
        DBHelper dbHelper = new DBHelper(NationActivity.this);
        //데이터를 조회해 올 데이터베이스 객체 생성
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //soccer 테이블에서 nation을 전부 가져오는 것
        Cursor cursor = db.rawQuery("select nation from soccer group by nation",new String[]{});
        //읽은 데이터를 순회하면서 list 에 추가
        list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        db.close();
        //ListView에 출력할 Adapter 만들기
        adapter = new ArrayAdapter<>(NationActivity.this,android.R.layout.simple_list_item_1,list);
        //ListView와 Adapter 연결
        listView.setAdapter(adapter);

        //listView에서 항목을 클릭했을 때를 처리하는
        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(
                    AdapterView<?> adapterView, View view, int i, long l){
                //선택한 데이터 찾아오기
                String nation = list.get(i);
                //하위 데이터를 출력할 PlayerActivity의
                //Intent를 생성하고 데이터를 설정하고 출력
                Intent intent = new Intent(NationActivity.this,PlayerActivity.class);
                intent.putExtra("nation",nation);
                startActivity(intent);
            }

        });

    }

}
