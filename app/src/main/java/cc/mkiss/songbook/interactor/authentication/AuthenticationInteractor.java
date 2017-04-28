package cc.mkiss.songbook.interactor.authentication;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import cc.mkiss.songbook.SongbookApplication;
import cc.mkiss.songbook.interactor.authentication.events.LoginEvent;
import cc.mkiss.songbook.repository.Repository;

public class AuthenticationInteractor {
    @Inject
    Repository repository;
    @Inject
    EventBus eventBus;

    public AuthenticationInteractor() {
        SongbookApplication.injector.inject(this);
    }

    public void login(String username, String password) {
        LoginEvent event = new LoginEvent();
        eventBus.post(event);
    }
}
