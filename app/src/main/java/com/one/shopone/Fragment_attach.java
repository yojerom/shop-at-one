package com.one.shopone;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by sathurshan on 7/17/2017.
 */

public  class Fragment_attach {

    public static void attach_fragment_to_activity(FragmentManager fragmentManager,int fragment_container,Fragment work_fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment_container,work_fragment);
        fragmentTransaction.commit();
    }
    public static void replace_fragment_to_activity(FragmentManager fragmentManager,int fragment_container,Fragment work_fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragment_container,work_fragment);
        fragmentTransaction.commit();
    }
}
