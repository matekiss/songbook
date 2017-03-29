package cc.mkiss.songbook.ui.song;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.mkiss.songbook.R;
import cc.mkiss.songbook.model.Song;

public class SongActivity extends AppCompatActivity implements SongScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
    }

    @Override
    public void showSong(Song song) {

    }

    @Override
    public void editSong(Song song) {

    }
}
