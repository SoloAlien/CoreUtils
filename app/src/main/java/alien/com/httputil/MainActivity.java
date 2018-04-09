package alien.com.httputil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import alien.com.entity.HttpResult;
import alien.com.entity.UserInfo;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.text);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit2.Call<HttpResult<UserInfo>> call= HttpUtil.getInstance().getApi().getUserCount();
                call.enqueue(new Callback<HttpResult<UserInfo>>() {
                    @Override
                    public void onResponse(retrofit2.Call<HttpResult<UserInfo>> call, Response<HttpResult<UserInfo>> response) {
                        text.setText(response.body().getData().getUserCount()+"");
                    }

                    @Override
                    public void onFailure(retrofit2.Call<HttpResult<UserInfo>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "onFailure: "+t.getMessage() );
                    }
                });
            }
        });
    }
}
