package cc.mkiss.songbook.model;

import com.orm.SugarRecord;

public class Song extends SugarRecord {
    private String title;
    private String lyrics;
    private boolean favorite;

    public Song() {
        this.title = "";
        this.lyrics = "";
        this.favorite = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
