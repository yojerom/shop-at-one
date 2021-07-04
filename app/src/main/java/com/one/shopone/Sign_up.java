package com.one.shopone;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sathurshan on 7/25/2017.
 */

public class Sign_up extends Fragment{
    private EditText mfname;
    private EditText mlname;
    private EditText memail;
    private EditText mnewpassword;
    private EditText mdob;
    private EditText mmobile;
    private EditText mlocation;
    private boolean magree = false;
    private Button mcontinue;
    private CheckBox checks;
    private FirebaseAuth mAuth;
    private ProgressDialog mprogress;
    private Snackbar bar;
    private DatabaseReference mdatabase;
    private RadioGroup radioGroup;
    private String radio_gender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initialize(view);
        mprogress = new ProgressDialog(getActivity());
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");//get directory of firebase
        setlisteners();
        mcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectgender();
                startsign_up();

            }
        });

        return view;
    }
    public  void initialize(View view){
        mfname = (EditText)view.findViewById(R.id.firstname);
        mlname = (EditText)view.findViewById(R.id.lastname);
        memail = (EditText)view.findViewById(R.id.email);
        mnewpassword = (EditText)view.findViewById(R.id.newpassword);
        mdob = (EditText)view.findViewById(R.id.birth_date);
        mmobile = (EditText)view.findViewById(R.id.mobilenumber);
        mlocation = (EditText)view.findViewById(R.id.location);
        checks = (CheckBox)view.findViewById(R.id.agree);
        mcontinue = (Button) view.findViewById(R.id.sign_up);
        radioGroup =(RadioGroup)view.findViewById(R.id.group_gender);

        mAuth = FirebaseAuth.getInstance();
    }
    public void startsign_up(){
        if(TextUtils.isEmpty(mfname.getText().toString()) || TextUtils.isEmpty(mlname.getText().toString()) ||TextUtils.isEmpty(memail.getText().toString())||TextUtils.isEmpty(mnewpassword.getText().toString())||TextUtils.isEmpty(mdob.getText().toString())
                ||TextUtils.isEmpty(mmobile.getText().toString())||TextUtils.isEmpty(mlocation.getText().toString())||TextUtils.isEmpty(radio_gender)){
                    Toast.makeText(getActivity(),"Fields are empty.",Toast.LENGTH_LONG).show();
        } else if(magree==false){
            Toast.makeText(getActivity(),"Tick the check box...", Toast.LENGTH_LONG).show();

        } else {
            mprogress.setMessage("Signing up...");
            mprogress.show();
            signing_up();
        }
    }
    public void signing_up(){
        String email = memail.getText().toString();
        String password = mnewpassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mprogress.dismiss();
                    String uid= mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_id=mdatabase.child(uid);
                    current_user_id.child("Firstname").setValue(mfname.getText().toString());
                    current_user_id.child("Lastname").setValue(mlname.getText().toString());
                    current_user_id.child("EmailAddress").setValue(memail.getText().toString());
                    current_user_id.child("Birthdate").setValue(mdob.getText().toString());
                    current_user_id.child("Location").setValue(mlocation.getText().toString());
                    current_user_id.child("Mobileno").setValue(mmobile.getText().toString());
                    Toast.makeText(getActivity(),"Registered successfully", Toast.LENGTH_SHORT).show();
                }else{
                    mprogress.dismiss();
                    Toast.makeText(getActivity(),"Registering unsuccessfull", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void setlisteners(){
        checks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox t = (CheckBox) v;

                if(t.isChecked()){
                    magree = true;
                }
            }
        });
    }

    public void selectgender(){
        int selectid=radioGroup.getCheckedRadioButtonId();

        switch (selectid){
            case R.id.maleradiobtn:
                radio_gender="Male";
                break;
            case R.id.femaleradiobtn:
                radio_gender="Female";
                break;
        }
    }
}
