package cc.mkiss.songbook.ui.login;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import cc.mkiss.songbook.interactor.authentication.AuthenticationInteractor;
import cc.mkiss.songbook.interactor.authentication.events.LoginEvent;
import cc.mkiss.songbook.ui.Presenter;

import static cc.mkiss.songbook.SongbookApplication.injector;

public class LoginPresenter extends Presenter<LoginScreen> {
    @Inject
    AuthenticationInteractor interactor;
    @Inject
    Executor executor;
    @Inject
    EventBus eventBus;

    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        eventBus.register(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        eventBus.unregister(this);
    }

    public void login(final String username, final String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.login(username, password);
            }
        });
    }

    public void onEventMainThread(LoginEvent event) {

    }
}
