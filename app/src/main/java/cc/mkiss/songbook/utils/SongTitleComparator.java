package cc.mkiss.songbook.utils;

import java.util.Comparator;

import cc.mkiss.songbook.model.Song;


public class SongTitleComparator implements Comparator<Song> {
    private static final SongTitleComparator INSTANCE = new SongTitleComparator();

    private SongTitleComparator() {}

    public static SongTitleComparator getInstance() {
        return INSTANCE;
    }

    @Override
    public int compare(Song s1, Song s2) {
        return s1.getTitle().compareTo(s2.getTitle());
    }
}
