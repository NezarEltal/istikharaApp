package com.nikah101.istikharaapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView transliteration_txt;
    private final String location = "dua";

    public String previous_location = "";


    SeekBar seekbar;
    MediaPlayer mp;
    Runnable runnable;
    ImageButton playButton;
    Handler mHandler;


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

        //To create the media player
        mp = MediaPlayer.create(this, R.raw.dua_audio);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        playButton = this.findViewById(R.id.playButton);

        seekbar = findViewById(R.id.seekBar);
        seekbar.setMax(mp.getDuration());

        mHandler = new Handler();


        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekbar.setMax(mp.getDuration());
                changeSeekbar();
            }
        });


        /**
         *  This function determines what happens when the media finishes playing
         *  It is intentional that it doesn't return to zero after finishing (so it shows that it's done)
         */
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                playButton.setImageResource(R.drawable.baseline_play_arrow_black_18dp);

                try {
                    mp.prepare();
                }
                catch (IOException e){
                    Toast.makeText(getApplicationContext(), "ERROR",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });



        /**
         * This OnClick Listener does two things:
         * 1) It starts and stops the audio whenever it is clicked
         * 2) It changes the imageButton background from play to stop and vice versa
         */
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()){

                    mp.pause();
                    playButton.setImageResource(R.drawable.baseline_play_arrow_black_18dp);


                }
                else{
                    mp.start();
                    changeSeekbar();
                    playButton.setImageResource(R.drawable.baseline_pause_black_18dp);
                }

            }
        });


        /**
         * SeekBar tracker to keep the seekbar on the right part while
         * the media player is playing
         */
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mp.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void changeSeekbar(){
        seekbar.setProgress(mp.getCurrentPosition());
        if (mp.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekbar();
                }
            };
            mHandler.postDelayed(runnable, 1000);
        }
    }



    /**
     * If the app is closed out, the audio stops
     */
    @Override
    protected void onPause(){
        super.onPause();
        mp.stop();
        try {
            mp.prepare();
        }
        catch (IOException e){
            Toast.makeText(getApplicationContext(), "ERROR",
                    Toast.LENGTH_SHORT).show();
        }
        playButton.setImageResource(R.drawable.baseline_play_arrow_black_18dp);
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

    /**
     * Onclick tip:
     *
     * public void onClick(View view){
     *      switch (view.getId()){
     *          case R.id.button:
     *                  //does some stuff;
     *                   break;
     *           case R.id.otherId:
     *                 //does some other stuff;
     *                break;
     *        }
     * }
     */

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
            tv1.setText(getString(R.string.menu_button_hide));

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



