package com.nikah101.istikharaapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView transliteration_txt;
    private final String location = "dua";

    public String previous_location = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transliteration_txt = (TextView) findViewById(R.id.transliteration_txt);
        // hide until its title is clicked
        transliteration_txt.setVisibility(View.GONE);


        //We want to avoid a NullPointerException Here
        Intent intent = getIntent();
        previous_location = intent.getStringExtra("last_location");

        
    }

    /** Called when the user taps the Send button */
    public void changeToFAQActivity(View view) {
        if (previous_location != null && previous_location.equals("faq")){
            finish();
        }
        else {
            Intent intent = new Intent(this, FAQ.class);

            intent.putExtra("last_location", location);
            startActivity(intent);
        }
    }

    /** Function to send user to Deatils.java */
    public void changeToDetailsActivity(View view) {
        if (previous_location != null && previous_location.equals("details")){
            finish();
        }
        else {
            Intent intent = new Intent(this, Details.class);
            intent.putExtra("last_location", location);
            startActivity(intent);
        }
    }

    /**
     * onClick handler
     * shows or hides transliteration text
     */
    public void toggle_contents(View v){
        TextView tv1 = (TextView)findViewById(R.id.transliteration_title);

        if (transliteration_txt.isShown()){
            transliteration_txt.setVisibility(View.GONE);
            tv1.setText(getString(R.string.menu_button));
        }
        else{
            transliteration_txt.setVisibility(View.VISIBLE);
            tv1.setText("Transliteration: (Tap to hide)");

        }

    }

    public void zoom_in(View view){
        TextView[] tv = new TextView[3];
        tv[0] = findViewById(R.id.dua_al_istikhara);
        tv[1] = findViewById(R.id.translation_text);
        tv[2] = findViewById(R.id.transliteration_txt);


        for (int i = 0; i < tv.length; i++){
            float tv_size = tv[i].getTextSize();

            tv_size += 10;
            tv[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, tv_size);
        }



    }

    public void zoom_out(View view){

        float size = ((TextView) findViewById(R.id.dua_al_istikhara)).getTextSize();
        if (size <= 40){
            Toast.makeText(getApplicationContext(), R.string.zoom_out_error,
                    Toast.LENGTH_SHORT).show();
        }

        else{
            TextView[] tv = new TextView[3];
            tv[0] = findViewById(R.id.dua_al_istikhara);
            tv[1] = findViewById(R.id.translation_text);
            tv[2] = findViewById(R.id.transliteration_txt);


            for (int i = 0; i < tv.length; i++){
                float tv_size = tv[i].getTextSize();

                tv_size -= 10;
                tv[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, tv_size);
            }


        }



    }
}



