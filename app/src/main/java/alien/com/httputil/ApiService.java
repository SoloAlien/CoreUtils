package alien.com.httputil;

import alien.com.entity.HttpResult;
import alien.com.entity.Test;
import alien.com.entity.UserInfo;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Alien on 2018-04-09.
 */

public interface ApiService {
    @GET("hello")
    Call<HttpResult<UserInfo>> getUserCount();

    @Multipart
    @POST("upload")
    Call<HttpResult<Test>> getUploadInfo(@Part MultipartBody.Part file);
}
