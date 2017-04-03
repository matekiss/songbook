package cc.mkiss.songbook.repository;

import android.content.Context;

import java.util.List;

import cc.mkiss.songbook.model.Song;

public interface Repository {
    void open(Context context);

    void close();

    // Song
    List<Song> getSongs();

    Song addSong(Song song);

    Song getSong(long id);

    void updateSong(Song song);

    void removeSong(Song song);

    // Favorite
    List<Song> getFavorites();

    void addFavorite(Song song);

    void removeFavorite(Song song);

    boolean isInDb(Song song);
}