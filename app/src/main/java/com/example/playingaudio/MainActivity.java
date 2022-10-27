package com.example.playingaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView playPauseIcon;
    TextView nameTxt,startTxt,endTxt;
    SeekBar seekBar;
    int numOfSong = 1;
    int duration;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPauseIcon = findViewById(R.id.playImg);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.secrets);

        nameTxt = findViewById(R.id.txtName);
        startTxt = findViewById(R.id.txtStart);
        endTxt = findViewById(R.id.txtEnd);

        seekBar = findViewById(R.id.volumeSeekBar);
        seekBar.setMax(mediaPlayer.getDuration());

        duration = mediaPlayer.getDuration();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mediaPlayer.seekTo(i);
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


    public void play(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        } else {
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.ic_baseline_pause_24);
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }, 0, 1000);
        }
    }

    public void next(View view) {

        switch (numOfSong) {
            case 1:
                addSong(R.raw.apologize, "Apologize");
                numOfSong = 2;
                break;
            case 2:
                addSong(R.raw.stars, "Counting Stars");
                numOfSong = 3;
                break;
            case 3:
                addSong(R.raw.secrets, "Secrets");
                numOfSong = 1;
                break;
        }


        playPauseIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
    }





    public void back(View view) {
            switch (numOfSong) {
                case 1:
                    addSong(R.raw.stars, "Counting Stars");
                    numOfSong = 3;
                    break;
                case 2:
                    addSong(R.raw.secrets, "Secrets");
                    numOfSong = 1;
                    break;
                case 3:
                    addSong(R.raw.apologize, "Apologize");
                    numOfSong = 2;
                    break;
            }


                playPauseIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);



    }

    public void addSong(int path, String name){
        mediaPlayer.pause();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), path);
        seekBar.setMax(mediaPlayer.getDuration());
        duration = mediaPlayer.getDuration();
        nameTxt.setText("One Republic - "+name);
    }
}