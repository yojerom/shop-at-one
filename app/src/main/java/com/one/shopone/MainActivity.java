package com.one.shopone;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Login.OnNextListener,Login.Onauthlistener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.hide();
        }
        FragmentManager fragmentManager = getFragmentManager();
        Login login=new Login();
        Fragment_attach.attach_fragment_to_activity(fragmentManager,R.id.main_reltive_layout,login);

    }
    @Override
    public void setsignfragment(boolean up) {

        if(up==true){
            FragmentManager fragmentManager = getFragmentManager();
            Sign_up sign=new Sign_up();
            Fragment_attach.replace_fragment_to_activity(fragmentManager,R.id.main_reltive_layout,sign);
        }
    }


    @Override
    public void setauthid(String uid) {
        setid(uid);
    }

    public void setid(String uid){
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString(getString(R.string.userid),uid);
        editor.commit();
        startActivity(new Intent(MainActivity.this,search_result.class));
    }
}
