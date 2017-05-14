package cc.mkiss.songbook.interactor.authentication;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

import cc.mkiss.songbook.SongbookApplication;
import cc.mkiss.songbook.interactor.authentication.events.LoginEvent;
import cc.mkiss.songbook.interactor.authentication.events.LogoutEvent;
import cc.mkiss.songbook.model.Credentials;
import cc.mkiss.songbook.network.api.UserApi;
import cc.mkiss.songbook.repository.Repository;

public class AuthenticationInteractor {
    @Inject
    Repository repository;
    @Inject
    EventBus eventBus;

    @Inject
    UserApi userApi;

    public AuthenticationInteractor() {
        SongbookApplication.injector.inject(this);
    }

    public void login(String username, String password) {
        LoginEvent event = new LoginEvent();

        Credentials credentials = new Credentials(username, password);
        try {
            event.setToken(userApi.loginUser(credentials).execute().body());
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    public void logout() {
        LogoutEvent event = new LogoutEvent();

        try {
            userApi.logoutUser().execute();
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }
}
