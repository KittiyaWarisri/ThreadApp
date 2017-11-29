package com.example.saiby.threadapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //URL url = new ("https://cdn-images-1.medium.com/max/2000/1*66DC14YlN6slL_8_jNuAFg.jpeg");
    //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
    //ivYourImage.setImageBitmap(bmp);
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.imageView);

    }

    public void Thread (View v)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                URL url = null;
                try {

                    url =new URL("https://www.allkpop.com/upload/2017/03/af_org/Big-Bang-TOP_1489028141_af_org.jpg");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Bitmap bmp = null;
                try {
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final ImageView imageView = (ImageView)findViewById(R.id.imageView);
                final Bitmap finalBmp = bmp;
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(finalBmp);
                    }
                });

            }
        }).start();

    }
    public void  AsyncTask(View v){
        new LoadImageTask().execute("http://s.isanook.com/wo/0/ud/4/21581/a3.jpg");
    }

    private class LoadImageTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected Bitmap doInBackground(String...urls) {
            URL url = null;
            try {
                url = new URL("http://s.isanook.com/wo/0/ud/4/21581/a3.jpg");
                InputStream is = url.openConnection().getInputStream();
                Bitmap bitMap = BitmapFactory.decodeStream(is);
                return bitMap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
            //return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        protected void onPostExcute(Bitmap result) {
            super.onPostExecute(result);
            img.setImageBitmap(result);
        }

    }

}
