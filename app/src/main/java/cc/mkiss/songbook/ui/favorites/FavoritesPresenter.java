package cc.mkiss.songbook.ui.favorites;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import cc.mkiss.songbook.interactor.songs.SongsInteractor;
import cc.mkiss.songbook.interactor.songs.events.GetFavoritesEvent;
import cc.mkiss.songbook.model.Song;
import cc.mkiss.songbook.ui.Presenter;

import static cc.mkiss.songbook.SongbookApplication.injector;


public class FavoritesPresenter extends Presenter<FavoritesScreen> {
    @Inject
    SongsInteractor interactor;
    @Inject
    Executor executor;
    @Inject
    EventBus eventBus;

    @Override
    public void attachScreen(FavoritesScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        eventBus.register(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        eventBus.unregister(this);
    }

    public void handleSong(Song song) {

    }

    public void searchSong(final String keyword) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.getFavorites(keyword);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GetFavoritesEvent event) {
        if (screen == null || event.getSongs() == null) {
            return;
        }

        screen.showSongs(event.getSongs());
    }
}
