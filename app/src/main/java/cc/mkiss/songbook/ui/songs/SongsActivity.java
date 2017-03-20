package cc.mkiss.songbook.ui.songs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import cc.mkiss.songbook.R;
import cc.mkiss.songbook.SongbookApplication;
import cc.mkiss.songbook.model.Song;

public class SongsActivity extends AppCompatActivity implements SongsScreen {
    @Inject
    SongsPresenter songsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        SongbookApplication.injector.inject(this);
    }

    @Override
    public void showSongs(List<Song> songs) {

    }

    @Override
    public void showSong(Long id) {

    }
}
