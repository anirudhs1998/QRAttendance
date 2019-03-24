package com.example.present_stud;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubjectList extends AppCompatActivity {

    ListView lv;
    String  rollno = "";
    ArrayList<Integer> subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();
       rollno = intent.getStringExtra("Roll");


        try{
            setContentView(R.layout.activity_subject_list);
            lv=(ListView)findViewById(R.id.listView);
            String arr[]=new String[7];
            for(int i=0;i<7;i++){
                arr[i]="CS0"+(i+1);
            }
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    if(intent.hasExtra("Roll")) {
                        //Avoid Intent


                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                                for (DataSnapshot child : children) {
                                    StudentInformation std = child.getValue(StudentInformation.class);


                                    if(rollno.equals(std.getRollno())){
                                        subject = std.getSubjects();
                                        if(subject.get(position).equals(-1))
                                        {
                                            Toast.makeText(getApplicationContext(),"Student not in this subject",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            int val = subject.get(position);
                                            child.getRef().child("subjects").child(String.valueOf(position)).setValue(val+1001);

                                            Toast.makeText(getApplicationContext(),"Attendance marked",Toast.LENGTH_SHORT).show();
                                        }


                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                    else{
                        Intent intent = new Intent(SubjectList.this, AttendanceActivity.class);
                        intent.putExtra("position", position + "");
                        startActivity(intent);
                    }

                }
            });
        }catch(Exception e){
            Log.e("Exception is" ,e.toString());
        }
    }
}
