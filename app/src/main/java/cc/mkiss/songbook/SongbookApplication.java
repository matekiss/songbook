package cc.mkiss.songbook;

import android.app.Application;

import cc.mkiss.songbook.ui.UIModule;

public class SongbookApplication extends Application {
    public static SongbookApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerSongbookApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
    }
}
