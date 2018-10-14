package com.nikah101.istikharaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public void zoom_in(View view){
        TextView[] tv = new TextView[12];
        tv[0] = findViewById(R.id.overview_header);
        tv[1] = findViewById(R.id.overview_subheader);
        tv[2] = findViewById(R.id.overview_content);
        tv[3] = findViewById(R.id.istikhara_prayer_header);
        tv[4] = findViewById(R.id.prerequisites_header);
        tv[5] = findViewById(R.id.prerequisites_content);
        tv[6] = findViewById(R.id.how_header);
        tv[7] = findViewById(R.id.how_content);
        tv[8] = findViewById(R.id.outcome_header);
        tv[9] = findViewById(R.id.outcome_content);
        tv[10] = findViewById(R.id.conclusion_header);
        tv[11] = findViewById(R.id.conclusion_content);


        for (int i = 0; i < tv.length; i++){
            float tv_size = tv[i].getTextSize();

            tv_size += 10;
            tv[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, tv_size);
        }



    }

    public void zoom_out(View view){

        float size = ((TextView) findViewById(R.id.overview_content)).getTextSize();
        if (size <= 30){
            Toast.makeText(getApplicationContext(), "Can't zoom out any further",
                    Toast.LENGTH_SHORT).show();
        }

        else{
            TextView[] tv = new TextView[12];
            tv[0] = findViewById(R.id.overview_header);
            tv[1] = findViewById(R.id.overview_subheader);
            tv[2] = findViewById(R.id.overview_content);
            tv[3] = findViewById(R.id.istikhara_prayer_header);
            tv[4] = findViewById(R.id.prerequisites_header);
            tv[5] = findViewById(R.id.prerequisites_content);
            tv[6] = findViewById(R.id.how_header);
            tv[7] = findViewById(R.id.how_content);
            tv[8] = findViewById(R.id.outcome_header);
            tv[9] = findViewById(R.id.outcome_content);
            tv[10] = findViewById(R.id.conclusion_header);
            tv[11] = findViewById(R.id.conclusion_content);


            for (int i = 0; i < tv.length; i++){
                float tv_size = tv[i].getTextSize();

                tv_size -= 10;
                tv[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, tv_size);
            }


        }



    }


}
