package com.nikah101.istikharaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FAQ extends AppCompatActivity {
    public String previous_location = "";
    final private String location = "faq";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        //Get the previous location sent by previous intent
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

    public void changeToDetailsActivity(View view){
        if (previous_location.equals("details")){
            finish();
        }
        else{
            Intent intent = new Intent(this, Details.class);
            intent.putExtra("last_location", location);
            startActivity(intent);
        }
    }
}
