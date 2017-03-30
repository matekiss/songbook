package cc.mkiss.songbook.ui.editor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.mkiss.songbook.R;
import cc.mkiss.songbook.SongbookApplication;
import cc.mkiss.songbook.model.Song;

public class EditorActivity extends AppCompatActivity implements EditorScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        SongbookApplication.injector.inject(this);
    }

    @Override
    public void showSong(Song song) {

    }
}
