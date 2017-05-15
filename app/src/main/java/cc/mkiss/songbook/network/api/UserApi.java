package cc.mkiss.songbook.network.api;

import cc.mkiss.songbook.model.Credentials;
import cc.mkiss.songbook.model.Token;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserApi {
  
  /**
   * Logs user into the system
   * 
   * @param body Credentials for login
   * @return Call<Token>
   */
  
  @POST("user/login")
  Call<Token> loginUser(
          @Body Credentials body
  );

  
  /**
   * Logs out current logged in user session
   * 
   * @return Call<Void>
   */
  
  @GET("user/logout")
  Call<Void> logoutUser();
    

  
}
