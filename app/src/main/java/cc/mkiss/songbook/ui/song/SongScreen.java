package cc.mkiss.songbook.ui.song;

import cc.mkiss.songbook.model.Song;

public interface SongScreen {
    void showSong(Song song);

    void editSong(Song song);
}
