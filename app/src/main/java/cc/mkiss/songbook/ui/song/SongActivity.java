package cc.mkiss.songbook.ui.song;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import javax.inject.Inject;

import cc.mkiss.songbook.R;
import cc.mkiss.songbook.SongbookApplication;
import cc.mkiss.songbook.model.Song;

public class SongActivity
        extends AppCompatActivity
        implements SongScreen
        , CompoundButton.OnCheckedChangeListener {
    public static final String EXTRA_SONG_ID = "cc.mkiss.songbook.ui.song.extra.SONG_ID";

    @Inject
    SongPresenter songPresenter;
    private Menu menu;
    private CheckBox star;
    private Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SongbookApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        songPresenter.attachScreen(this);

        Intent intent = getIntent();
        songPresenter.handleSongId(intent.getLongExtra(EXTRA_SONG_ID, 0));
    }

    @Override
    protected void onStop() {
        super.onStop();
        songPresenter.detachScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.song, menu);

        this.menu = menu;
        this.star = (CheckBox) menu.getItem(0).getActionView();
        this.star.setOnCheckedChangeListener(this);

        if (song != null) {
            star.setChecked(song.isFavorite());
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (song == null) {
            return;
        }

        songPresenter.handleStar(song, isChecked);
    }

    @Override
    public void showSong(Song song) {
        this.song = song;

        getSupportActionBar().setTitle(song.getTitle());

        TextView tvLyrics = (TextView) findViewById(R.id.tvLyrics);
        tvLyrics.setText(song.getLyrics());

        if (menu != null) {
            star.setChecked(song.isFavorite());
        }
    }

    @Override
    public void editSong(Song song) {

    }
}
