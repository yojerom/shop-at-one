package com.one.shopone;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by sathurshan on 7/17/2017.
 */

public class Login extends Fragment{
    TextView newuser;
    OnNextListener onNextListener;
    Onauthlistener onauthlistener;
    private EditText mEmailaddress;
    private EditText mpassword;
    private Button mlogin;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog lprogress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login,container,false);
        newuser = (TextView)view.findViewById(R.id.reg_user);

        mEmailaddress = (EditText)view.findViewById(R.id.emailaddress);
        mpassword = (EditText)view.findViewById(R.id.password);

        mlogin=(Button)view.findViewById(R.id.loginbtn);

        mauth = FirebaseAuth.getInstance();
        lprogress = new ProgressDialog(getActivity());

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();

                if(user!=null){
                    onauthlistener.setauthid(user.getUid());
                }
            }
        };

        mlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                signin();
            }
        });

        newuser.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                onNextListener.setsignfragment(true);
            }
        });

        return view;
    }

    public interface OnNextListener{
        public void setsignfragment(boolean up);
    }
    public interface Onauthlistener{
        public void setauthid(String uid);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            onNextListener=(OnNextListener)activity;
            onauthlistener=(Onauthlistener)activity;
        }catch(Exception e){
            Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
        }
    }
    public void signin(){
        String emailfield = mEmailaddress.getText().toString();
        String passwordfield = mpassword.getText().toString();

       if(TextUtils.isEmpty(emailfield)||TextUtils.isEmpty(passwordfield)){
           Toast.makeText(getActivity(),"Fields are empty",Toast.LENGTH_LONG).show();
       }else{
           lprogress.setMessage("Login...");
           lprogress.show();
           mauth.signInWithEmailAndPassword(emailfield,passwordfield).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(!task.isSuccessful()){
                       lprogress.dismiss();
                       Toast.makeText(getActivity(),"SignIn problem",Toast.LENGTH_LONG).show();
                   }else{
                       lprogress.dismiss();
                   }
               }
           });
       }

    }

    @Override
    public void onStart() {
        super.onStart();
        mauth.addAuthStateListener(mAuthListener);
    }
}


