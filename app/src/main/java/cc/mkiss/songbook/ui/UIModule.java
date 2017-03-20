package cc.mkiss.songbook.ui;

import android.content.Context;

import javax.inject.Singleton;

import cc.mkiss.songbook.ui.login.LoginPresenter;
import cc.mkiss.songbook.ui.login.LoginScreen;
import cc.mkiss.songbook.ui.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }
}
