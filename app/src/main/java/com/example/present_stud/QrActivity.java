package com.example.present_stud;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrActivity extends AppCompatActivity {

    Button showDetails;
    ImageView Qr;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);


        showDetails = findViewById(R.id.showDetails);
        Qr = findViewById(R.id.Qr);

        showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SelectRollNo.class);
                startActivity(intent);
                finish();
            }
        });
        Intent i = getIntent();
        String email = i.getStringExtra("Email");


        QRGEncoder qrgEncoder = new QRGEncoder(email,null, QRGContents.Type.TEXT,1000);

        try{
            bitmap = qrgEncoder.encodeAsBitmap();
            Qr.getLayoutParams().height = 500;
            Qr.getLayoutParams().width = 500;
            Qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


    }
}
