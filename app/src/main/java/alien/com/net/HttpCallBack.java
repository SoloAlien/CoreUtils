package alien.com.net;


import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alien on 2018-04-28.
 */

public abstract class HttpCallBack<T> implements Callback<HttpResult<T>>{
    private Context mContext;
    public HttpCallBack(Context context) {
        mContext=context;
    }
    public  abstract void onSuccess(T t);

    @Override
    public void onResponse(Call<HttpResult<T>> call, Response<HttpResult<T>> response) {
       if (response.isSuccessful()){ //code >= 200 && code < 300
           //When return code match our defined sucess code,return result
           if (HttpConstant.RESULT_SUCCESS_CODE==response.body().getCode()){
                onSuccess(response.body().getData());
           }else onFailure(call,new Throwable(response.body().getCode()+":"+response.body().getMsg()));
       }else {
           onFailure(call,new Throwable(response.raw().code()+":"+response.raw().message()));
       }
    }

    @Override
    public void onFailure(Call<HttpResult<T>> call, Throwable t) {
        Log.e("TAG", "网络异常: "+t.getMessage() );
    }
}
