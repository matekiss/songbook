package cc.mkiss.songbook.interactor;

import cc.mkiss.songbook.interactor.songs.SongsInteractor;
import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @Provides
    public SongsInteractor provideSongsInteractor() {
        return new SongsInteractor();
    }
}
