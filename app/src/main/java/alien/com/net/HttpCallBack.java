package alien.com.net;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by Alien on 2018-04-28.
 */

public abstract class HttpCallBack<T> implements Callback<HttpResult<T>> {
    private Context mContext;
    LoadingDialog loadingDialog;

    public HttpCallBack(Context context) {
        mContext = context;
        loadingDialog=new LoadingDialog(context);
        loadingDialog.show();
    }

    public abstract void onSuccess(T t);

    @Override
    public void onResponse(Call<HttpResult<T>> call, Response<HttpResult<T>> response) {
        loadingDialog.dismiss();
        if (response.isSuccessful()) { //code >= 200 && code < 300
            //When return code match our defined sucess code,return result
            if (HttpConstant.RESULT_SUCCESS_CODE == response.body().getCode()) {
                onSuccess(response.body().getData());
            } else onFailure(call, new Throwable(response.body().getCode() + ":" + response.body().getMsg()));
        } else {
            onFailure(call, new Throwable(response.raw().code() + ":" + response.raw().message()));
        }
    }

    @Override
    public void onFailure(Call<HttpResult<T>> call, Throwable t) {
        loadingDialog.dismiss();
        if (t instanceof JsonSyntaxException) {
            Log.e("TAG", "Json解析异常: " + t.getMessage());
        } else if (t instanceof SocketTimeoutException){
            Log.e("TAG", "连接超时: " + t.getMessage());
        } else if (t instanceof UnknownHostException){
            Log.e("TAG", "主机解析异常: " + t.getMessage());
        }else if (t instanceof UnknownServiceException){
            Log.e("TAG", "未知服务器异常: " + t.getMessage());
        }else if(t instanceof ConnectException){
            Log.e("TAG", "网络连接异常: " + t.getMessage());
        } else if (t instanceof HttpException) {
            Log.e("TAG", "网络异常: " + t.getMessage());
        }else {
            Log.e("TAG", "未知异常: " + t.getMessage());
        }
    }

}
