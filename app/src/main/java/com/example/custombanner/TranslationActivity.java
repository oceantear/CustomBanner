package com.example.custombanner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TranslationActivity extends AppCompatActivity {

    View text;
    View text2;
    View text3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        text= findViewById(R.id.txt);
        text2= findViewById(R.id.txt2);
        text3= findViewById(R.id.txt3);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i("jimmy","before left : "+text2.getLeft()+" top : "+text2.getTop());
        Log.i("jimmy","before x : "+text2.getX()+" y : "+text2.getY());
        text2.setScaleX(1.2f);
        text2.setScaleY(1.2f);
        Log.i("jimmy","after left : "+text2.getLeft()+" top : "+text2.getTop());
        Log.i("jimmy","after x : "+text2.getX()+" y : "+text2.getY());

        text.setTranslationX(10);
        text3.setTranslationX(-10);

        Log.i("jimmy","after2 left : "+text2.getLeft()+" top : "+text2.getTop());
        Log.i("jimmy","after2 x : "+text2.getX()+" y : "+text2.getY());
    }
}
