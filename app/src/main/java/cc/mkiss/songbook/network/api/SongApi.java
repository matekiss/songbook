package cc.mkiss.songbook.network.api;

import java.util.List;

import cc.mkiss.songbook.model.Song;
import retrofit2.Call;
import retrofit2.http.*;

public interface SongApi {
  
  /**
   * Returns all songs
   * 
   * @return Call<List<Song>>
   */
  
  @GET("song")
  Call<List<Song>> getSongs();
    

  
  /**
   * Add a new song
   * 
   * @param body Song object that needs to be added
   * @return Call<Song>
   */
  
  @POST("song")
  Call<Song> addSong(
          @Body Song body
  );

  
  /**
   * Find song by ID
   * Returns a single song
   * @param songId ID of song to return
   * @return Call<Song>
   */
  
  @GET("song/{songId}")
  Call<Song> getSongById(
          @Path("songId") Long songId
  );

  
  /**
   * Update an existing song
   * 
   * @param body Song object that needs to be updated
   * @return Call<Void>
   */
  
  @PUT("song/{songId}")
  Call<Void> updateSong(
          @Body Song body
  );

  
  /**
   * Deletes a song
   * 
   * @param songId Song ID to delete
   * @return Call<Void>
   */
  
  @DELETE("song/{songId}")
  Call<Void> deleteSong(
          @Path("songId") Long songId
  );

  
}
