package cc.mkiss.songbook.ui.songs;

import java.util.List;

import cc.mkiss.songbook.model.Song;

public interface SongsScreen {
    void showSongs(List<Song> songs);

    void showSong(Song song);
}
