package cc.mkiss.songbook.ui.favorites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import cc.mkiss.songbook.R;
import cc.mkiss.songbook.model.Song;

public class FavoritesActivity extends AppCompatActivity implements FavoritesScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
    }

    @Override
    public void showSongs(List<Song> songs) {

    }

    @Override
    public void showSong(Song song) {

    }
}
