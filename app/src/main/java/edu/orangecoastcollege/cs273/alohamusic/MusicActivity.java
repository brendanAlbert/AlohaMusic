package edu.orangecoastcollege.cs273.alohamusic;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * MusicActivity is the second Controller of Aloha Music but it serves as the main part of the app.
 *
 * The usual connecting of Button widgets occurs.
 *
 * Two new types of Objects are the VideoView and MediaPlayer.
 *
 * VideoView is used to play videos and the MediaPlayer is used to play synchronous audio files.
 */
public class MusicActivity extends AppCompatActivity {

    // References to UI components
    Button ukuleleButton;
    Button ipuButton;
    Button hulaButton;

    VideoView hulaVideoView;

    MediaPlayer ukuleleMediaPlayer;
    MediaPlayer ipuMediaPlayer;

    /**
     * onCreate is called when the App first starts and also when the orientation
     * of the device changes.
     *
     * The content view is set and widgets in the View are connected.
     *
     * When instantiating a MediaPlayer object, we call the static create method and
     * pass it two parameters, the context (in this case 'this') and the R.raw resource
     * we wish to associate with this MediaPlayer.
     *
     * Instantiating a VideoView involves labeling a Uniform Resource Identifier (URI).
     * The three parts of a URI are: scheme, host and path.
     * android.resource:// is the scheme
     * the package name (edu.orangecoastcollege.cs273.alohamusic) is the path
     * and the path is R.raw.ukulele or R.raw.ipu.
     * We pass the URI to the setVideoURI method to instantiate the VideoView.
     *
     * We also instantiate and pass a new MediaController object to the setMediaController
     * method.  This allows a user to play, pause, seek, and rewind to parts of the video.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        // Associate the components
        ukuleleButton = (Button) findViewById(R.id.ukuleleButton);
        ipuButton = (Button) findViewById(R.id.ipuButton);
        hulaButton = (Button) findViewById(R.id.hulaButton);
        hulaVideoView = (VideoView) findViewById(R.id.hulaVideoView);

        // Associate the Media Players:
        ukuleleMediaPlayer = MediaPlayer.create(this, R.raw.ukulele);
        ipuMediaPlayer = MediaPlayer.create(this, R.raw.ipu);

        // Associate the Video View with its URI
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.hula;
        hulaVideoView.setVideoURI(Uri.parse(uri));
        // Create a MediaController for the VideoView
        // MediaController = controls (play/pause/forward/rewind)
        hulaVideoView.setMediaController(new MediaController(this));
    }

    /**
     * playMedia is called whenever the user taps any of the three buttons.
     * Whether the button plays or pauses is handled in the switch statement.
     * If a player is playing, the buttons for the other two players are hidden.
     * @param v
     */
    // Method will handle all 3 button clicks
    // Use the button id to see which was clicked
    public void playMedia(View v)
    {
        // Make a decision based on the id of the view
        switch(v.getId())
        {
            case R.id.ukuleleButton:
                // if it's playing, pause it
                // else, play it
                if (ukuleleMediaPlayer.isPlaying())
                {
                    ukuleleMediaPlayer.pause();
                    // Change the text
                    ukuleleButton.setText(R.string.ukulele_button_play_text);
                    // Show other two buttons
                    ipuButton.setVisibility(View.VISIBLE);
                    hulaButton.setVisibility(View.VISIBLE);
                }
                else
                {
                    ukuleleMediaPlayer.start();
                    ukuleleButton.setText(R.string.ukulele_button_pause_text);
                    ipuButton.setVisibility(View.INVISIBLE);
                    hulaButton.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.ipuButton:
                if (ipuMediaPlayer.isPlaying())
                {
                    ipuMediaPlayer.pause();
                    ipuButton.setText(R.string.ipu_button_play_text);
                    ukuleleButton.setVisibility(View.VISIBLE);
                    hulaButton.setVisibility(View.VISIBLE);
                }
                else
                {
                    ipuMediaPlayer.start();
                    ipuButton.setText(R.string.ipu_button_pause_text);
                    ukuleleButton.setVisibility(View.INVISIBLE);
                    hulaButton.setVisibility(View.INVISIBLE);

                }
                break;
            case R.id.hulaButton:
                if(hulaVideoView.isPlaying())
                {
                    hulaVideoView.pause();
                    hulaButton.setText(R.string.hula_button_watch_text);
                    ukuleleButton.setVisibility(View.VISIBLE);
                    ipuButton.setVisibility(View.VISIBLE);
                }
                else
                {
                    hulaVideoView.start();
                    hulaButton.setText(R.string.hula_button_pause_text);
                    ukuleleButton.setVisibility(View.INVISIBLE);
                    ipuButton.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    // Override onStop method to release MediaPlayers
    // Prevent memory leaks

    /**
     * onStop is called whenever an activity becomes invisible or is finished.
     * For example, when the user rotates the device.  onStop is called and releases
     * the memory for the two MediaPlayers before more are created in onCreate.
     */
    @Override
    protected void onStop() {
        super.onStop();
        ukuleleMediaPlayer.release();
        ipuMediaPlayer.release();
    }
}
