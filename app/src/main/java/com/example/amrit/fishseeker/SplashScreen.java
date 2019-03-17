package com.example.amrit.fishseeker;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private SoundTask soundTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        soundTask = (SoundTask) new SoundTask().execute();

        startAnimations();
        setupSkipButton();
    }

    private void setupSkipButton() {
        Button skipBtn = (Button) findViewById(R.id.splash_skip);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundTask.stopMedia();
                soundTask.cancel(true);
                Intent intent = MainMenuActivity.createIntent(SplashScreen.this);
                startActivity(intent);
                finish();
            }
        });
    }

    private void startAnimations() {
        // Fish Animations
        ImageView imageView = (ImageView) findViewById(R.id.splash_image);
        Animation fade_scale = AnimationUtils.loadAnimation(this, R.anim.fade_scale);
        imageView.setAnimation(fade_scale);

        // Header and skip button Animations
        TextView header = (TextView) findViewById(R.id.splash_header);
        Button skipBtn = (Button) findViewById(R.id.splash_skip);
        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        header.setAnimation(fade_in);
        skipBtn.setAnimation(fade_in);
    }


    private class SoundTask extends AsyncTask<Void, Integer, Void>{

        MediaPlayer mediaPlayer;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mediaPlayer = MediaPlayer.create(SplashScreen.this, R.raw.flute);
            mediaPlayer.start();
            for(int i = 0; i < 50; i++){
                try{
                    Thread.sleep(100);
                } catch(Exception e){
                    Log.i("Exception", e.toString());
                }
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i("Prog:", Integer.toString(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();

            Intent intent = MainMenuActivity.createIntent(SplashScreen.this);
            startActivity(intent);
            finish();

            super.onPostExecute(aVoid);
        }

        public void stopMedia() {
            Log.i("Cancel3", "true");
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }
}
