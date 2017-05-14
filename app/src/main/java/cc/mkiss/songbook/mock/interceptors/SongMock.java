package cc.mkiss.songbook.mock.interceptors;

import android.net.Uri;

import cc.mkiss.songbook.network.NetworkConfig;
import cc.mkiss.songbook.repository.MemoryRepository;
import cc.mkiss.songbook.repository.Repository;
import cc.mkiss.songbook.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static cc.mkiss.songbook.mock.interceptors.MockHelper.makeResponse;

public class SongMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "song")
                && request.method().equals("GET")) {
            Repository repository = new MemoryRepository();
            repository.open(null);
            responseString = GsonHelper.getGson().toJson(repository.getSongs(""));
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "song")
                && request.method().equals("POST")) {
            responseString = "";
            responseCode = 200;
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "song")
                && request.method().equals("GET")) {
            Repository repository = new MemoryRepository();
            repository.open(null);
            responseString = GsonHelper.getGson().toJson(repository.getSongs("").get(0));
            responseCode = 200;
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "song")
                && request.method().equals("PUT")) {
            responseString = "";
            responseCode = 200;
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "song")
                && request.method().equals("DELETE")) {
            responseString = "";
            responseCode = 200;
        } else {
            responseString = "";
            responseCode = 200;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
