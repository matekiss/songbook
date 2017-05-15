package cc.mkiss.songbook.mock.interceptors;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import cc.mkiss.songbook.model.Song;
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
            responseString = GsonHelper.getGson().toJson(songSeed());
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

    private static List<Song> songSeed() {
        List<Song> songs = new ArrayList<>();

        Song song1 = new Song();
        song1.setId((long) 1337);
        song1.setTitle("Example song 1");
        song1.setLyrics("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus molestie felis luctus pellentesque varius. Phasellus elit justo, tempus laoreet aliquet ac, imperdiet tristique libero. Sed eu urna sed ligula tempor malesuada at eu tortor. Curabitur posuere sapien sed vestibulum volutpat. Curabitur tempor, est nec mollis venenatis, felis sem sagittis est, a sodales mi est sit amet urna. Integer dolor velit, molestie non lacinia in, aliquet quis metus. Pellentesque aliquet porttitor porttitor.");
        song1.setFavorite(false);
        songs.add(song1);

        Song song2 = new Song();
        song2.setId((long) 1338);
        song2.setTitle("Example song 2");
        song2.setLyrics("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus molestie felis luctus pellentesque varius. Phasellus elit justo, tempus laoreet aliquet ac, imperdiet tristique libero. Sed eu urna sed ligula tempor malesuada at eu tortor. Curabitur posuere sapien sed vestibulum volutpat. Curabitur tempor, est nec mollis venenatis, felis sem sagittis est, a sodales mi est sit amet urna. Integer dolor velit, molestie non lacinia in, aliquet quis metus. Pellentesque aliquet porttitor porttitor.");
        song2.setFavorite(true);
        songs.add(song2);

        Song song3 = new Song();
        song3.setId((long) 1339);
        song3.setTitle("Example song 3");
        song3.setLyrics("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus molestie felis luctus pellentesque varius. Phasellus elit justo, tempus laoreet aliquet ac, imperdiet tristique libero. Sed eu urna sed ligula tempor malesuada at eu tortor. Curabitur posuere sapien sed vestibulum volutpat. Curabitur tempor, est nec mollis venenatis, felis sem sagittis est, a sodales mi est sit amet urna. Integer dolor velit, molestie non lacinia in, aliquet quis metus. Pellentesque aliquet porttitor porttitor.");
        song3.setFavorite(true);
        songs.add(song3);

        return songs;
    }
}
