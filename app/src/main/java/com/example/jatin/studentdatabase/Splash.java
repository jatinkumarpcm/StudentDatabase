package com.example.jatin.studentdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.flaviofaria.kenburnsview.KenBurnsView;

public class Splash extends AppCompatActivity {

    ImageView imageView;
    KenBurnsView kenBurnsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //imageView = (ImageView) findViewById(R.id.imageView);
        kenBurnsView = (KenBurnsView) findViewById(R.id.imageview);
        getSupportActionBar().hide();
        Thread thread = new Thread()
        {

            public  void run()
            {
                try{
                    sleep(4000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        };
        thread.start();

    }
}
