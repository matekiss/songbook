package cc.mkiss.songbook.mock.interceptors;

import android.net.Uri;

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

        responseString = "";
        responseCode = 200;

        return makeResponse(request, headers, responseCode, responseString);
    }
}
