package cc.mkiss.songbook.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import cc.mkiss.songbook.model.Song;

public class SugarORMRepository implements Repository {
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<Song> getSongs(String keyword) {
        return SugarRecord.find(Song.class, "title LIKE ?", "%" + keyword + "%");
    }

    @Override
    public Song addSong(Song song) {
        long id = song.save();

        song.setId(song.save());
        return song;
    }

    @Override
    public Song getSong(long id) {
        return SugarRecord.findById(Song.class, id);
    }

    @Override
    public void updateSong(Song song) {
        Song songFromDb = SugarRecord.findById(Song.class, song.getId());
        songFromDb.setTitle(song.getTitle());
        songFromDb.setLyrics(song.getLyrics());
        songFromDb.setFavorite(song.isFavorite());
        songFromDb.save();
    }

    @Override
    public void removeSong(Song song) {
        Song songFromDb = SugarRecord.findById(Song.class, song.getId());
        songFromDb.delete();
    }

    @Override
    public List<Song> getFavorites(String keyword) {
        return SugarRecord.find(Song.class, "favorite = ? and title LIKE ?", Boolean.toString(true), "%" + keyword + "%");
    }

    @Override
    public void addFavorite(Song song) {
        Song songFromDb = SugarRecord.findById(Song.class, song.getId());
        songFromDb.setFavorite(true);
        songFromDb.save();
    }

    @Override
    public void removeFavorite(Song song) {
        Song songFromDb = SugarRecord.findById(Song.class, song.getId());
        songFromDb.setFavorite(false);
        songFromDb.save();
    }

    @Override
    public boolean isInDb(Song song) {
        return SugarRecord.findById(Song.class, song.getId()) != null;
    }
}
