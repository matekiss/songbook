package cc.mkiss.songbook.utils;

public class TokenManager {
    private static final TokenManager INSTANCE = new TokenManager();

    private TokenManager() {}

    public static TokenManager getInstance() {
        return INSTANCE;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
