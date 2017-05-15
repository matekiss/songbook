package cc.mkiss.songbook.ui.editor;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import cc.mkiss.songbook.R;
import cc.mkiss.songbook.SongbookApplication;
import cc.mkiss.songbook.model.Song;

public class EditorActivity
        extends AppCompatActivity
        implements EditorScreen
        , View.OnClickListener {
    public static final String EXTRA_SONG_ID = "cc.mkiss.songbook.ui.editor.extra.SONG_ID";

    @Inject
    EditorPresenter editorPresenter;
    private Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        SongbookApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        editorPresenter.attachScreen(this);

        Intent intent = getIntent();
        editorPresenter.handleSongId(intent.getLongExtra(EXTRA_SONG_ID, 0));
    }

    @Override
    protected void onStop() {
        super.onStop();
        editorPresenter.detachScreen();
    }

    @Override
    public void onClick(View v) {
        EditText etTitle = (EditText) findViewById(R.id.etTitle);
        song.setTitle(etTitle.getText().toString());

        EditText etLyrics = (EditText) findViewById(R.id.etLyrics);
        song.setLyrics(etLyrics.getText().toString());

        editorPresenter.saveSong(song);
    }

    @Override
    public void showSong(Song song) {
        this.song = song;

        EditText etTitle = (EditText) findViewById(R.id.etTitle);
        etTitle.setText(song.getTitle());

        EditText etLyrics = (EditText) findViewById(R.id.etLyrics);
        etLyrics.setText(song.getLyrics());
    }
}
