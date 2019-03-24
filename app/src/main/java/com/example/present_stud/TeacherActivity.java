package com.example.present_stud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class TeacherActivity extends AppCompatActivity  {

    Button b1,b2,b3,b4;
    private ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button4);
        b4 = findViewById(R.id.button3);


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent = new Intent(getApplicationContext(),ScanActivity.class);
            startActivity(intent);


            }
        });
    }



    public void fn(View view){
        if(view==b1){
            Intent intent=new Intent(this,SubjectList.class);
            startActivity(intent);
        }
        else if(view==b2){
            Intent intent=new Intent(TeacherActivity.this,AddStudentActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent=new Intent(this,SelectRollNo.class);
            startActivity(intent);
        }
    }


}
