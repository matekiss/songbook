package cc.mkiss.songbook.ui.main;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import cc.mkiss.songbook.interactor.songs.SongsInteractor;
import cc.mkiss.songbook.interactor.songs.events.AddSongEvent;
import cc.mkiss.songbook.ui.Presenter;

import static cc.mkiss.songbook.SongbookApplication.injector;

public class MainPresenter extends Presenter<MainScreen> {
    @Inject
    SongsInteractor interactor;
    @Inject
    Executor executor;
    @Inject
    EventBus eventBus;

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void handleAdd() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.addSong();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddSongEvent event) {
        if (screen == null) {
            return;
        }

        screen.showSong(event.getSong());
    }
}
