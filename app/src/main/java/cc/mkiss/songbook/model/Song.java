package cc.mkiss.songbook.model;

import com.orm.dsl.Table;

@Table
public class Song {
    private Long id;
    private String title;
    private String lyrics;
    private boolean favorite;

    public Song() {
        this.id = null;
        this.title = "";
        this.lyrics = "";
        this.favorite = false;
    }

    public Song(Long id, String title, String lyrics, boolean favorite) {
        this.id = id;
        this.title = title;
        this.lyrics = lyrics;
        this.favorite = favorite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
