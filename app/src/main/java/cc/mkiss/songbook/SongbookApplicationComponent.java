package cc.mkiss.songbook;

import javax.inject.Singleton;

import cc.mkiss.songbook.ui.UIModule;
import cc.mkiss.songbook.ui.editor.EditorActivity;
import cc.mkiss.songbook.ui.favorites.FavoritesActivity;
import cc.mkiss.songbook.ui.login.LoginActivity;
import cc.mkiss.songbook.ui.main.MainActivity;
import cc.mkiss.songbook.ui.song.SongActivity;
import cc.mkiss.songbook.ui.songs.SongsActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface SongbookApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(SongsActivity songsActivity);

    void inject(FavoritesActivity favoritesActivity);

    void inject(SongActivity songActivity);

    void inject(EditorActivity editorActivity);
}