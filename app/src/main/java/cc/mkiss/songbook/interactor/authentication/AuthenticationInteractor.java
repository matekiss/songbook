package cc.mkiss.songbook.interactor.authentication;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import cc.mkiss.songbook.SongbookApplication;
import cc.mkiss.songbook.interactor.authentication.events.LoginEvent;
import cc.mkiss.songbook.interactor.authentication.events.LogoutEvent;
import cc.mkiss.songbook.model.Credentials;
import cc.mkiss.songbook.model.Song;
import cc.mkiss.songbook.network.api.SongApi;
import cc.mkiss.songbook.network.api.UserApi;
import cc.mkiss.songbook.repository.Repository;

public class AuthenticationInteractor {
    @Inject
    Repository repository;
    @Inject
    EventBus eventBus;

    @Inject
    UserApi userApi;
    @Inject
    SongApi songApi;

    public AuthenticationInteractor() {
        SongbookApplication.injector.inject(this);
    }

    public void login(String username, String password) {
        LoginEvent event = new LoginEvent();

        Credentials credentials = new Credentials(username, password);
        String token = null;
        try {
            token = userApi.loginUser(credentials).execute().body().getToken();
            populateRepository();
        } catch (Exception e) {
            event.setThrowable(e);
        }
        event.setToken(token);

        eventBus.post(event);
    }

    private void populateRepository() throws IOException {
        try {
            for (Song song : songApi.getSongs().execute().body()) {
                repository.addSong(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        LogoutEvent event = new LogoutEvent();

        try {
            userApi.logoutUser().execute();
            purgeRepository();
        } catch (Exception e) {
            event.setThrowable(e);
        }

        eventBus.post(event);
    }

    private void purgeRepository() {
        for (Song song : repository.getSongs("")) {
            repository.removeSong(song);
        }
    }
}
