package com.one.shopone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Profile extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    Map<String,Object> td;
    ListView listView1;
    ArrayAdapter<String> arrayAdapter;
    String []list1=new String[7];
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userid=user.getUid();
        String link = "https://shop-36c78.firebaseio.com/Users/"+userid;

        myRef = database.getReferenceFromUrl(link);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String value = dataSnapshot.getValue(String.class);
                td = (HashMap<String,Object>) dataSnapshot.getValue();
                int i=0;
                Iterator myVeryOwnIterator = td.keySet().iterator();
                while(myVeryOwnIterator.hasNext()) {
                    String key=(String)myVeryOwnIterator.next();
                    String value=(String)td.get(key);

                    list1[i]=key+" : "+value;
                    i++;
                }
                execute1();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Profile.this,"Failed to read value",Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void execute1(){
        listView1=(ListView)findViewById(R.id.listview);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
        listView1.setAdapter(arrayAdapter);
    }
}
