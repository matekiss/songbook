package cc.mkiss.songbook;

import javax.inject.Singleton;

import cc.mkiss.songbook.interactor.InteractorModule;
import cc.mkiss.songbook.interactor.authentication.AuthenticationInteractor;
import cc.mkiss.songbook.interactor.songs.SongsInteractor;
import cc.mkiss.songbook.mock.MockNetworkModule;
import cc.mkiss.songbook.repository.RepositoryModule;
import cc.mkiss.songbook.ui.UIModule;
import cc.mkiss.songbook.ui.editor.EditorActivity;
import cc.mkiss.songbook.ui.editor.EditorPresenter;
import cc.mkiss.songbook.ui.favorites.FavoritesFragment;
import cc.mkiss.songbook.ui.favorites.FavoritesPresenter;
import cc.mkiss.songbook.ui.login.LoginActivity;
import cc.mkiss.songbook.ui.login.LoginPresenter;
import cc.mkiss.songbook.ui.main.MainActivity;
import cc.mkiss.songbook.ui.song.SongActivity;
import cc.mkiss.songbook.ui.song.SongPresenter;
import cc.mkiss.songbook.ui.songs.SongsFragment;
import cc.mkiss.songbook.ui.songs.SongsPresenter;
import dagger.Component;

@Singleton
@Component(modules = {InteractorModule.class, MockNetworkModule.class, RepositoryModule.class, UIModule.class})
public interface SongbookApplicationComponent {
    void inject(SongbookApplication songbookApplication);

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(LoginPresenter loginPresenter);

    void inject(SongsFragment songsFragment);

    void inject(SongsPresenter songsPresenter);

    void inject(FavoritesFragment favoritesFragment);

    void inject(FavoritesPresenter favoritesPresenter);

    void inject(SongActivity songActivity);

    void inject(SongPresenter songPresenter);

    void inject(EditorActivity editorActivity);

    void inject(EditorPresenter editorPresenter);

    void inject(SongsInteractor songsInteractor);

    void inject(AuthenticationInteractor authenticationInteractor);
}