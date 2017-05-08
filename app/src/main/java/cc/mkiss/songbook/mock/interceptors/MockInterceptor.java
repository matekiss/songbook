package cc.mkiss.songbook.mock.interceptors;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import cc.mkiss.songbook.network.NetworkConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static cc.mkiss.songbook.mock.interceptors.MockHelper.makeResponse;

public class MockInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return process(chain.request());
    }

    public Response process(Request request) {

        Uri uri = Uri.parse(request.url().toString());

        Log.d("Test Http Client", "URL call: " + uri.toString());
        Headers headers = request.headers();


        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "song")) {
            return SongMock.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "user")) {
            return UserMock.process(request);
        }

        return makeResponse(request, headers, 404, "Unknown");
    }
}
