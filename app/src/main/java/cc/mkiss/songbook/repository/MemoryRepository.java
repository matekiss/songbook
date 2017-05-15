package cc.mkiss.songbook.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.mkiss.songbook.model.Song;

public class MemoryRepository implements Repository {
    private static long songIdSequence = 1;

    private static Map<Long, Song> songs;

    private long getNextSongId() {
        return songIdSequence++;
    }

    @Override
    public void open(Context context) {
        songs = new HashMap<>();
    }

    @Override
    public void close() {

    }

    @Override
    public List<Song> getSongs(String keyword) {
        List<Song> songList = new ArrayList<>();
        for (Song song : songs.values()) {
            if (song.getTitle().contains(keyword)) {
                songList.add(song);
            }
        }

        return songList;
    }

    @Override
    public Song addSong(Song song) {
        song.setId(getNextSongId());
        songs.put(song.getId(), song);
        return song;
    }

    @Override
    public Song getSong(long id) {
        if (!songs.containsKey(id)) {
            throw new IllegalArgumentException("Invalid ID.");
        }

        return songs.get(id);
    }

    @Override
    public void updateSong(Song song) {
        if (!songs.containsKey(song.getId())) {
            throw new IllegalArgumentException("Invalid ID.");
        }

        songs.put(song.getId(), song);
    }

    @Override
    public void removeSong(Song song) {
        if (!songs.containsKey(song.getId())) {
            throw new IllegalArgumentException("Invalid ID.");
        }

        songs.remove(song.getId());
    }

    @Override
    public List<Song> getFavorites(String keyword) {
        List<Song> favorites = new ArrayList<>();
        for (Song song : songs.values()) {
            if (song.isFavorite() && song.getTitle().contains(keyword)) {
                favorites.add(song);
            }
        }
        return favorites;
    }

    @Override
    public void addFavorite(Song song) {
        songs.get(song.getId()).setFavorite(true);
    }

    @Override
    public void removeFavorite(Song song) {
        songs.get(song.getId()).setFavorite(false);
    }

    @Override
    public boolean isInDb(Song song) {
        return songs.containsKey(song.getId());
    }
}
