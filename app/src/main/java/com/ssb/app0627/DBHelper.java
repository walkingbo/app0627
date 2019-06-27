package com.ssb.app0627;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //생성자 -Context를 넘겨받아서 상위 클래스를 호출
    public DBHelper(Context context){
        super(context,"database.sqlite",null,1);

    }

    //처음 사용하려고 했을 때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db){
        //테이블을 만들고 샘플데이터를 삽입
        String sql ="create table soccer(_id integer primary key autoincrement, nation text, player text)";
        //SQL 실행
        db.execSQL(sql);
        //샘플 데이터 삽입
        db.execSQL("insert into soccer(nation,player) values('대한민국','박지성')");
        db.execSQL("insert into soccer(nation,player) values('영국','웨인 루니')");
        db.execSQL("insert into soccer(nation,player) values('네덜란드','반 니스텔루이')");
        db.execSQL("insert into soccer(nation,player) values('포르투갈','크리스티아누 호날두')");
        db.execSQL("insert into soccer(nation,player) values('프랑스','그리즈만')");
        db.execSQL("insert into soccer(nation,player) values('프랑스','음바페')");
        db.execSQL("insert into soccer(nation,player) values('포르투갈','루이스 피구')");
        db.execSQL("insert into soccer(nation,player) values('세르비아','비디치')");
        db.execSQL("insert into soccer(nation,player) values('영국','존 테리')");

    }

    //데이터베이스 버전이 변경되었을 때 호출되는 메소드
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //테이블을 삭제하고 다시 생성
        db.execSQL("drop table soccer");
        onCreate(db);
    }


}
