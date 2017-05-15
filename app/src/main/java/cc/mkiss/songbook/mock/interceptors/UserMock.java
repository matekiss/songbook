package cc.mkiss.songbook.mock.interceptors;

import android.net.Uri;

import cc.mkiss.songbook.model.Token;
import cc.mkiss.songbook.network.NetworkConfig;
import cc.mkiss.songbook.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static cc.mkiss.songbook.mock.interceptors.MockHelper.makeResponse;

public class UserMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "user/login")
                && request.method().equals("POST")) {
            responseString = GsonHelper.getGson().toJson(new Token("secret"));
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "user/logout")
                && request.method().equals("GET")) {
            responseString = "";
            responseCode = 200;
        } else {
            responseString = "";
            responseCode = 404;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
