package cc.mkiss.songbook.ui.song;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import cc.mkiss.songbook.interactor.songs.SongsInteractor;
import cc.mkiss.songbook.interactor.songs.events.GetSongEvent;
import cc.mkiss.songbook.model.Song;
import cc.mkiss.songbook.ui.Presenter;

import static cc.mkiss.songbook.SongbookApplication.injector;

public class SongPresenter extends Presenter<SongScreen> {
    @Inject
    SongsInteractor interactor;
    @Inject
    Executor executor;
    @Inject
    EventBus eventBus;

    @Override
    public void attachScreen(SongScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        eventBus.register(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        eventBus.unregister(this);
    }

    public void handleEdit(Song song) {

    }

    public void handleStar(Song song, boolean isChecked) {
        if (isChecked) {
            interactor.addFavorite(song);
        } else {
            interactor.removeFavorite(song);
        }
    }

    public void onEventMainThread(GetSongEvent event) {
        if (screen == null) {
            return;
        }

        screen.showSong(event.getSong());
    }
}
