package cc.mkiss.songbook;

import javax.inject.Singleton;

import cc.mkiss.songbook.ui.UIModule;
import cc.mkiss.songbook.ui.main.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface SongbookApplicationComponent {
    void inject(MainActivity mainActivity);
}