package edu.orangecoastcollege.cs273.alohamusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * SplashActivity is the first activity a user sees.
 *
 * This Controller is essentially a stall of 3 seconds before the main part of the app,
 * MusicActivity, runs.
 *
 * AlohaMusic is a relatively simple app and it already contains all of the files and information
 * needed to work correctly.  For a more complicated app that requires say a call to an external
 * api or database, we might need to retrieve some data before we can pass control to the user.
 * If we did not pre load data in this way, the app could seem unresponsive to the user.
 *
 * Part of the job of the developer is considering these trade-offs.  How much up-front delay
 * is acceptable and how much could be loaded "lazily" later.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * onCreate in SplashActivity is the first method called when AlohaMusic is run.
     *
     * A TimerTask object is created which will fire an Intent to take the user
     * to MusicActivity.
     *
     * A Timer object is created and the TimerTask is scheduled by passing itself and the
     * delay of 3000 milliseconds (3 secs).  The TimerTask will run when the Timer finishes.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Create a TimerTask to defer the loading of MusicActivity by 3 seconds
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Finish the current SplashActivity, then:
                finish();
                // Launch the MusicActivity
                Intent musicIntent = new Intent(SplashActivity.this, MusicActivity.class);

                startActivity(musicIntent);
            }
        };

        // Run the timer task after 3 seconds (3000 milliseconds)
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }
}
