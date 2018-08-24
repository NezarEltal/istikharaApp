package com.nikah101.istikharaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Details extends AppCompatActivity {
    String previous_location = "";
    private final String location = "details";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Get the intent that started this activity to extract the string sent
        Intent intent = getIntent();
        previous_location = intent.getStringExtra("last_location");

    }

    public void goBackOneActivity(View view) {
        finish();
    }


    public void changeToMainActivity(View view){
        if (previous_location.equals("dua")){
            finish();
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("last_location", location);
            startActivity(intent);
        }
    }

    public void changeToFAQActivity(View view){
        if (previous_location.equals("faq")){
            finish();
        }
        else{
            Intent intent = new Intent(this, FAQ.class);
            intent.putExtra("last_location", location);
            startActivity(intent);
        }
    }


}
