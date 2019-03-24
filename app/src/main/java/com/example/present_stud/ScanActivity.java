package com.example.present_stud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
private ZXingScannerView zXingScannerView;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        final String email = result.getText();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                try{
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        StudentInformation stud1 = dataSnapshot1.getValue(StudentInformation.class);
                        if(stud1.getEmail().equals(email))
                        {
                            String roll = stud1.getRollno();
                           // Toast.makeText(getApplicationContext(),roll,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),SubjectList.class);

                            intent.putExtra("Roll",roll);
                            startActivity(intent);
                        }

                    }

                }
                catch (Exception e) {
                    Log.e("Exception is", e.toString());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        zXingScannerView.resumeCameraPreview(this);
    }
}
