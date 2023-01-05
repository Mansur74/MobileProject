package com.example.mobileproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.adapters.MusicAdapter;
import com.example.mobileproject.adapters.SuitAdapter;
import com.example.mobileproject.models.Music;
import com.example.mobileproject.models.Suit;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity {

    ListView musicList;
    List<Music> musics;
    MusicAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        toolbar = findViewById(R.id.toolbar);
        ImageButton backButton = toolbar.findViewById(R.id.back);
        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("Musics");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        musicList = findViewById(R.id.music_list);
        musics = new ArrayList<>();

        Music music = new Music("Canon In D", "from Pachelbel Canon, Johann Pachelbel", "canon");
        Music music2 = new Music("Instrumental Piano", "Anonim", "instrumental");
        Music music3 = new Music("Epic Music", "Anonim", "epic");
        Music music4 = new Music("Beautiful Piano", "Anonim", "piano");

        musics.add(music);
        musics.add(music2);
        musics.add(music3);
        musics.add(music4);

        adapter = new MusicAdapter(MusicActivity.this, musics);
        musicList.setAdapter(adapter);



    }
}