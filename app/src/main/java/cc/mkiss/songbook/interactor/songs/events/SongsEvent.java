package cc.mkiss.songbook.interactor.songs.events;

import java.util.List;

import cc.mkiss.songbook.interactor.Event;
import cc.mkiss.songbook.model.Song;

public abstract class SongsEvent extends Event {
    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
