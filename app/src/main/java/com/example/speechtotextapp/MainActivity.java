package com.example.speechtotextapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView speechBtn;
    EditText speechTxt;

    private static final int RECOGNIZER_RESULT=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar aBar;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FF1C1C"));
        aBar.setBackgroundDrawable(cd);

        speechBtn= findViewById(R.id.speechBtn);
        speechTxt= findViewById(R.id.speechText);

        speechBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent speechIntent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speech to text");
                startActivityForResult(speechIntent, RECOGNIZER_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == RECOGNIZER_RESULT && resultCode== RESULT_OK){
            ArrayList<String> matches= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            speechTxt.setText(matches.get(0).toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}