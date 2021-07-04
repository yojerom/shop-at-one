package com.one.shopone;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.one.shopone.adapter.Trendingadapter;
import com.one.shopone.model.Trending_details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sathurshan on 8/4/2017.
 */

public class FetchDataTask extends AsyncTask<Void,Void,Void> {
    private  String trendmart;
    private Trendingadapter mAdapter;
    public ArrayList<Trending_details> mTrendingcollection;

    public FetchDataTask(Trendingadapter mAdapter,ArrayList<Trending_details> mTrendingcollection) {
        this.mAdapter=mAdapter;
        this.mTrendingcollection=mTrendingcollection;
    }

    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        Uri builtUri = Uri.parse("http://api.walmartlabs.com/v1/trends?format=json&apiKey=jyuabbfc2b9xwwep3dxapfef");
        URL url;

        try{
            url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer=new StringBuffer();

            if(inputStream==null){
                return  null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null){
                buffer.append(line+"\n");
            }

            if(buffer.length()==0){
                return  null;
            }

            trendmart = buffer.toString();
            JSONObject jsonObject=new JSONObject(trendmart);

            Log.v("Response",jsonObject.toString());


            JSONArray trendArray = jsonObject.getJSONArray("items");

            for(int i=0;i <trendArray.length();i++){
                String name;
                String itemid;
                String imageurl;
                String brandname;
                String salesprice;
                String msrp;
                String rating;

                JSONObject jtrend = (JSONObject)trendArray.get(i);
                //jtrend = jtrend.getJSONObject("itemId");

                name = jtrend.getString("name");
                itemid = jtrend.getString("itemId");
                imageurl = jtrend.getString("largeImage");
                ;
                if(jtrend.optString("brandName").isEmpty())
                {
                    brandname = "---";
                }else{
                    brandname = jtrend.getString("brandName");
                }
                salesprice = "$"+jtrend.getString("salePrice");

                if(jtrend.optString("msrp").isEmpty()){
                    msrp = "Not given";
                }else{
                    msrp = "$"+jtrend.getString("msrp");
                }



                if(jtrend.optString("customerRating").isEmpty()){
                    rating = "0";
                }else{
                    rating = jtrend.getString("customerRating");
                }

                Trending_details trending_detail = new Trending_details();
                trending_detail.setName(name);
                trending_detail.setBrandName(brandname);
                trending_detail.setItemId(itemid);
                trending_detail.setSalePrice(salesprice);
                trending_detail.setLargeImage(imageurl);
                trending_detail.setMsrp(msrp);
                trending_detail.setCustomerRating(rating);

                mTrendingcollection.add(trending_detail);
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection != null){
                try {
                    reader.close();
                }catch (final IOException e){
                    Log.e("MainActivity","Error closing stram",e);
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mAdapter.notifyDataSetChanged();
    }
}
