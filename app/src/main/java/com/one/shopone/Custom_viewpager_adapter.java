package com.one.shopone;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by sathurshan on 8/3/2017.
 */

public class Custom_viewpager_adapter extends PagerAdapter {
    Context mcontext;
    LayoutInflater mlayout_inflater;
    int resource[]={R.drawable.profilepic,R.drawable.splash,R.drawable.logo};
    @Override
    public int getCount() {
        return resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==((LinearLayout)object);
    }
    public Custom_viewpager_adapter(Context context){
        mcontext=context;
        mlayout_inflater=(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemview=mlayout_inflater.inflate(R.layout.pager_item,container,false);
        ImageView imageView=(ImageView)itemview.findViewById(R.id.frontimageview);
        imageView.setImageResource(resource[position]);
        container.addView(itemview);

        return  itemview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
