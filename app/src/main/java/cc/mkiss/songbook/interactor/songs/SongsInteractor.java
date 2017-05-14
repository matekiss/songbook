package cc.mkiss.songbook.interactor.songs;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import cc.mkiss.songbook.SongbookApplication;
import cc.mkiss.songbook.interactor.songs.events.AddFavoriteEvent;
import cc.mkiss.songbook.interactor.songs.events.AddSongEvent;
import cc.mkiss.songbook.interactor.songs.events.GetFavoritesEvent;
import cc.mkiss.songbook.interactor.songs.events.GetSongEvent;
import cc.mkiss.songbook.interactor.songs.events.GetSongsEvent;
import cc.mkiss.songbook.interactor.songs.events.RemoveFavoriteEvent;
import cc.mkiss.songbook.interactor.songs.events.RemoveSongEvent;
import cc.mkiss.songbook.interactor.songs.events.UpdateSongEvent;
import cc.mkiss.songbook.model.Song;
import cc.mkiss.songbook.network.api.SongApi;
import cc.mkiss.songbook.repository.Repository;

public class SongsInteractor {
    @Inject
    Repository repository;
    @Inject
    EventBus eventBus;

    @Inject
    SongApi songApi;

    public SongsInteractor() {
        SongbookApplication.injector.inject(this);
    }

    public void getSongs() {
        getSongs(null);
    }

    public void getSongs(String keyword) {
        if (keyword == null) {
            keyword = "";
        }

        GetSongsEvent event = new GetSongsEvent();
        try {
            List<Song> songs = repository.getSongs(keyword);
            event.setSongs(songs);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void getSong(Long id) {
        GetSongEvent event = new GetSongEvent();
        try {
            Song song = repository.getSong(id);
            event.setSong(song);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void getFavorites() {
        getFavorites(null);
    }

    public void getFavorites(String keyword) {
        if (keyword == null) {
            keyword = "";
        }

        GetFavoritesEvent event = new GetFavoritesEvent();
        try {
            List<Song> songs = repository.getFavorites(keyword);
            event.setSongs(songs);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void addFavorite(Song song) {
        AddFavoriteEvent event = new AddFavoriteEvent();
        try {
            repository.addFavorite(song);
            updateRemoteSongFromRepository(song);
            event.setSong(song);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void removeFavorite(Song song) {
        RemoveFavoriteEvent event = new RemoveFavoriteEvent();
        try {
            repository.removeFavorite(song);
            updateRemoteSongFromRepository(song);
            event.setSong(song);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void addSong() {
        AddSongEvent event = new AddSongEvent();
        try {
            Song song = repository.addSong(new Song());
            addRemoteSongFromRepository(song);
            event.setSong(song);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void removeSong(Song song) {
        RemoveSongEvent event = new RemoveSongEvent();
        try {
            repository.removeSong(song);
            deleteRemoteSong(song);
            event.setSong(song);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void updateSong(Song song) {
        UpdateSongEvent event = new UpdateSongEvent();
        try {
            repository.updateSong(song);
            updateRemoteSongFromRepository(song);
            event.setSong(song);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    private void addRemoteSongFromRepository(Song song) throws IOException {
        song = repository.getSong(song.getId());
        songApi.addSong(song);
    }

    private void updateRemoteSongFromRepository(Song song) throws IOException {
        song = repository.getSong(song.getId());
        songApi.updateSong(song).execute();
    }

    private void deleteRemoteSong(Song song) throws IOException {
        songApi.deleteSong(song.getId()).execute();
    }
}
