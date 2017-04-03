package cc.mkiss.songbook.interactor.songs.events;

import cc.mkiss.songbook.interactor.Event;
import cc.mkiss.songbook.model.Song;


public class AddSongEvent extends Event {
    private Song song;

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
