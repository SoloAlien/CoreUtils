package alien.com.httputil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alien.com.entity.HttpResult;
import alien.com.entity.Test;
import alien.com.entity.UserInfo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Alien on 2018-04-09.
 * All the examples here are standard requests(这里的所有样例都是标准请求)
 */

public interface ApiService {
    @GET("hello")
    Call<HttpResult<UserInfo>> getUserCount();

    @Multipart
    @POST("fileupload")
    Call<HttpResult<Test>> getUploadInfo(@Part MultipartBody.Part file);

    @Multipart
    @POST("filesupload")
    Call<HttpResult<Test>> getFilesUpload(@Part List<MultipartBody.Part> fileList,@Part("hello") RequestBody hello);
}
