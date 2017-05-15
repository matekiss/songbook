package cc.mkiss.songbook.ui.editor;

import cc.mkiss.songbook.model.Song;

public interface EditorScreen {
    void showSong(Song song);

    void finish();
}
