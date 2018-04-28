package alien.com.net;

import java.util.List;

import alien.com.entity.Test;
import alien.com.entity.UserInfo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
