package cc.mkiss.songbook.network.api;

import retrofit2.Call;
import retrofit2.http.*;

public interface UserApi {
  
  /**
   * Logs user into the system
   * 
   * @param username The user name for login
   * @param password The password for login in clear text
   * @return Call<String>
   */
  
  @GET("user/login")
  Call<String> loginUser(
          @Query("username") String username, @Query("password") String password
  );

  
  /**
   * Logs out current logged in user session
   * 
   * @return Call<Void>
   */
  
  @GET("user/logout")
  Call<Void> logoutUser();
    

  
}
