package cc.mkiss.songbook.interactor.authentication.events;

import cc.mkiss.songbook.interactor.Event;

public class LoginEvent extends Event {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
