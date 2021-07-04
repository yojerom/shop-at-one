package com.one.shopone.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.one.shopone.R;
import com.one.shopone.model.Trending_details;

import java.util.ArrayList;

/**
 * Created by sathurshan on 8/4/2017.
 */

public class Trendingadapter extends RecyclerView.Adapter<Trendingadapter.TrendingHolder> {
    private ArrayList<Trending_details> mData;
    private Activity mActivity;

    public Trendingadapter(ArrayList<Trending_details> data, Activity activity) {
        this.mData = data;
        this.mActivity = activity;
    }

    @Override
    public TrendingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_items,parent,false);
        return new TrendingHolder(view);
    }

    @Override
    public void onBindViewHolder(TrendingHolder holder, int position) {
        Trending_details trending_details = mData.get(position);

        holder.setName(trending_details.getName());
        holder.setItemId(trending_details.getItemId());
        holder.setBrandName(trending_details.getBrandName());
        holder.setCustomerRating(trending_details.getCustomerRating());
        holder.setSalePrice(trending_details.getSalePrice());
        holder.setMsrp(trending_details.getMsrp());

        Glide.with(mActivity).load(trending_details.getLargeImage()).into(holder.productImageview);
    }

    @Override
    public int getItemCount() {
        if(mData == null)
            return 0;

        return mData.size();
    }

    public class TrendingHolder extends RecyclerView.ViewHolder{
        ImageView productImageview;
        TextView productname_txtview;
        String itemid_txtview;
        RatingBar rating_ratingview;
        TextView salesprice_txtview;
        TextView msrp_txtview;
        TextView brandname_txtview;

        TrendingHolder(View itemView){
            super(itemView);
            initializecomponents(itemView);
        }
        public void initializecomponents(View itemView){
            productImageview = (ImageView)itemView.findViewById(R.id.imageview_product);
            rating_ratingview = (RatingBar) itemView.findViewById(R.id.ratingbar);
            productname_txtview = (TextView)itemView.findViewById(R.id.nameofproduct);
            salesprice_txtview = (TextView)itemView.findViewById(R.id.price);
            msrp_txtview = (TextView)itemView.findViewById(R.id.msrp);
            brandname_txtview = (TextView)itemView.findViewById(R.id.brandname);
        }
        public void setName(String name) {
            productname_txtview.setText(name);
        }
        public void setItemId(String itemId) {
            itemid_txtview = itemId;
        }
        public void setCustomerRating(String customerRating) { rating_ratingview.setRating(Float.parseFloat(customerRating)); }
        public void setSalePrice(String salePrice) {
            salesprice_txtview.setText(salePrice);
        }
        public void setMsrp(String msrp) { msrp_txtview.setText(msrp); }
        public void setBrandName(String brandName) {
            brandname_txtview.setText(brandName);
        }


    }
}
