package alien.com.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import alien.com.entity.HttpResult;
import alien.com.entity.UserInfo;
import alien.com.httputil.HttpUtil;
import alien.com.httputil.R;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView text;
    private Toolbar titlebar;
    private Menu menu;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titlebar=findViewById(R.id.titlebar);

        //加载弹出菜单布局
        titlebar.inflateMenu(R.menu.menu);
        menu=titlebar.getMenu();
        //为弹出菜单item设置点击事件
        menu.findItem(R.id.item1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "糟糕，被点中了！", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //为导航按钮设置点击事件
        titlebar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+v.getId(), Toast.LENGTH_SHORT).show();
            }
        });




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
