package cc.mkiss.songbook.ui.editor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import cc.mkiss.songbook.interactor.songs.SongsInteractor;
import cc.mkiss.songbook.interactor.songs.events.GetSongEvent;
import cc.mkiss.songbook.interactor.songs.events.UpdateSongEvent;
import cc.mkiss.songbook.model.Song;
import cc.mkiss.songbook.ui.Presenter;

import static cc.mkiss.songbook.SongbookApplication.injector;

public class EditorPresenter extends Presenter<EditorScreen> {
    @Inject
    SongsInteractor interactor;
    @Inject
    Executor executor;
    @Inject
    EventBus eventBus;

    @Override
    public void attachScreen(EditorScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        eventBus.register(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        eventBus.unregister(this);
    }

    public void handleSongId(final long id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.getSong(id);
            }
        });
    }

    public void saveSong(final Song song) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.updateSong(song);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GetSongEvent event) {
        if (screen == null) {
            return;
        }

        screen.showSong(event.getSong());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UpdateSongEvent event) {
        if (screen == null) {
            return;
        }

        screen.finish();
    }
}
