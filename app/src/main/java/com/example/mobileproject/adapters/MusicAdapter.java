package com.example.mobileproject.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.activities.MusicActivity;
import com.example.mobileproject.models.Music;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music> {

    private final LayoutInflater inflater;
    private MediaPlayer mediaPlayer;
    private int id1 = -1;
    private int id2 = -1;

    Activity ctx;

    public MusicAdapter(Activity context, List<Music> views) {
        super(context, 0, views);
        inflater = LayoutInflater.from(context);
        ctx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Music musicData = getItem(position);
        View currencyView = inflater.inflate(R.layout.list_item_music ,null, true);

        TextView name = (TextView) currencyView.findViewById(R.id.name);
        TextView artist = (TextView) currencyView.findViewById(R.id.artist);
        ImageButton play = (ImageButton) currencyView.findViewById(R.id.play);
        ImageButton pause = (ImageButton) currencyView.findViewById(R.id.pause);
        ImageButton stop = (ImageButton) currencyView.findViewById(R.id.stop);

        String resourceIdT = musicData.getResourceId();

        Resources resources = ctx.getResources();
        final int resourceId = resources.getIdentifier(resourceIdT, "raw",
                ctx.getPackageName());

        name.setText(musicData.getName());
        artist.setText(musicData.getArtist());

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id2 = position;
                if(mediaPlayer == null)
                {
                    mediaPlayer = MediaPlayer.create(ctx, resourceId);
                    id1 = position;
                    id2 = position;
                }
                else if(id1 != id2 & mediaPlayer.isPlaying())
                {
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(ctx, resourceId);
                    id1 = position;
                    id2 = position;
                }

                else if(id1 != id2)
                {
                    mediaPlayer = MediaPlayer.create(ctx, resourceId);
                    id1 = position;
                    id2 = position;
                }

                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer != null)
                    mediaPlayer.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer != null)
                {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }


            }
        });

        return currencyView;
    }

}
