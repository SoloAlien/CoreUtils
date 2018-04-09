package alien.com.httputil;

import alien.com.entity.HttpResult;
import alien.com.entity.UserInfo;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Alien on 2018-04-09.
 */

public interface ApiService {
    @GET("hello")
    Call<HttpResult<UserInfo>> getUserCount();
}
