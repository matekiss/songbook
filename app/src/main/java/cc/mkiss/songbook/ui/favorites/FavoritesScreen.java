package cc.mkiss.songbook.ui.favorites;

import java.util.List;

import cc.mkiss.songbook.model.Song;

public interface FavoritesScreen {
    void showSongs(List<Song> songs);

    void showSong(Song song);
}
