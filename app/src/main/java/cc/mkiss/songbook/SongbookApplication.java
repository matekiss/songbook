package cc.mkiss.songbook;

import android.app.Application;

import javax.inject.Inject;

import cc.mkiss.songbook.repository.Repository;
import cc.mkiss.songbook.ui.UIModule;

public class SongbookApplication extends Application {
    public static SongbookApplicationComponent injector;

    @Inject
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerSongbookApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
        injector.inject(this);

        repository.open(getApplicationContext());
    }
}
