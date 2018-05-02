package alien.com.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by Alien on 2018-04-09.
 */

public class HttpUtil {
    private Retrofit retrofit;
    private ApiService httpService;

    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }
    private HttpUtil() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                //modify by zqikai 20160317 for 对http请求结果进行统一的预处理 GosnResponseBodyConvert
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ResponseConvertFactory.create())//add a self define converter
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HttpConstant.BASE_URL)
                .build();
    }

    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ApiService getApi() {
        httpService = retrofit.create(ApiService.class);
        return httpService;
    }
}
