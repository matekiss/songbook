package cc.mkiss.songbook.ui.songs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import cc.mkiss.songbook.interactor.songs.SongsInteractor;
import cc.mkiss.songbook.interactor.songs.events.AddFavoriteEvent;
import cc.mkiss.songbook.interactor.songs.events.AddSongEvent;
import cc.mkiss.songbook.interactor.songs.events.GetSongsEvent;
import cc.mkiss.songbook.interactor.songs.events.RemoveFavoriteEvent;
import cc.mkiss.songbook.model.Song;
import cc.mkiss.songbook.ui.Presenter;

import static cc.mkiss.songbook.SongbookApplication.injector;

public class SongsPresenter extends Presenter<SongsScreen> {
    @Inject
    SongsInteractor interactor;
    @Inject
    Executor executor;
    @Inject
    EventBus eventBus;

    @Override
    public void attachScreen(SongsScreen screen) {
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

    public void handleStar(Song song, boolean isChecked) {
        if (isChecked) {
            interactor.addFavorite(song);
        } else {
            interactor.removeFavorite(song);
        }
    }

    public void searchSong(final String keyword) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.getSongs(keyword);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GetSongsEvent event) {
        if (screen == null || event.getSongs() == null) {
            return;
        }

        screen.showSongs(event.getSongs());
    }
}
