package com.example.sqlpro;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView songtitle ;
    Button play, forward, rewind, pause, stop, reset;
    MediaPlayer mediaPlayer ;
    int starttime = 0 ;
    int stopttime = 0;
    int forwardtime = 5000 ;
    int backwardtime = 5000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        songtitle = findViewById(R.id.songname) ;
        play = findViewById(R.id.play) ;
        forward = findViewById(R.id.forward) ;
        rewind = findViewById(R.id.rewind) ;
        pause = findViewById(R.id.pause) ;
        stop = findViewById(R.id.stop) ;
        reset = findViewById(R.id.restart) ;
        mediaPlayer = MediaPlayer.create(this, R.raw.first) ;
        songtitle.setText("Magnolia.mp4");

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Playing Media now", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Pausing the Media", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Media stopped", Toast.LENGTH_SHORT).show();
                mediaPlayer.stop();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                mediaPlayer.start();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentpos = mediaPlayer.getCurrentPosition() ;
                if((currentpos+forwardtime) <= (stopttime = mediaPlayer.getDuration())){
                    mediaPlayer.seekTo(currentpos+forwardtime);
                }
            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentpos = mediaPlayer.getCurrentPosition() ;
                if((currentpos-backwardtime) >= starttime){
                    mediaPlayer.seekTo(currentpos-backwardtime);
                }
            }
        });



    }
}