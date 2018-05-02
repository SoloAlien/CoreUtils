package alien.com.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.util.ArrayList;

import alien.com.net.HttpCallBack;
import alien.com.net.HttpResult;
import alien.com.entity.Test;
import alien.com.entity.UserInfo;
import alien.com.net.FileUploadUtil;
import alien.com.net.HttpUtil;
import alien.com.net.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button button, uploadbutton;
    private TextView text;
    private Toolbar titlebar;
    private Menu menu;
    private Context mContext;
    private static int REQUEST_CODE=100;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titlebar = findViewById(R.id.titlebar);
        mContext=this;

        //加载弹出菜单布局
        titlebar.inflateMenu(R.menu.menu);
        menu = titlebar.getMenu();
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
                Toast.makeText(MainActivity.this, "" + v.getId(), Toast.LENGTH_SHORT).show();
            }
        });


        text = findViewById(R.id.text);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点中了", Toast.LENGTH_SHORT).show();
                retrofit2.Call<HttpResult<UserInfo>> call = HttpUtil.getInstance().getApi().getUserCount();
                call.enqueue(new HttpCallBack<UserInfo>(mContext) {
                    @Override
                    public void onSuccess(UserInfo userInfo) {
                        text.setText(userInfo.getUserCount()+"");
                    }
                });
            }
        });


        //文件上传
        uploadbutton = findViewById(R.id.uploadbutton);
        uploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Honor.mp3");
                MultipartBody.Part part = FileUploadUtil.fileToMultiBodyPart(file);

                retrofit2.Call<HttpResult<Test>> call = HttpUtil.getInstance().getApi().getUploadInfo(part);
                call.enqueue(new HttpCallBack<Test>(mContext) {
                    @Override
                    public void onSuccess(Test test) {
                        Toast.makeText(MainActivity.this, "" + test.getResult(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        findViewById(R.id.filesUpload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "one.txt");
                File file1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "two.txt");
                File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "three.txt");
                ArrayList<File> files = new ArrayList<>();
                files.add(file);
                files.add(file1);
                files.add(file2);
                ArrayList<MultipartBody.Part> parts = (ArrayList<MultipartBody.Part>) FileUploadUtil.filesToMultipartBodyParts(files);
                RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "图片的描述");


                Call<HttpResult<Test>> call = HttpUtil.getInstance().getApi().getFilesUpload(parts, description);
                call.enqueue(new Callback<HttpResult<Test>>() {
                    @Override
                    public void onResponse(Call<HttpResult<Test>> call, Response<HttpResult<Test>> response) {
                        Toast.makeText(MainActivity.this, "" + response.body().getData().getResult(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<HttpResult<Test>> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.getMessage());
                    }
                });
            }
        });
        findViewById(R.id.scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),REQUEST_CODE);
            }
        });

        findViewById(R.id.generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String context="hello world!";
                Bitmap bitmap=CodeUtils.createImage(context,300,300, BitmapFactory.decodeResource(Resources.getSystem(),R.mipmap.ic_launcher));
                ((ImageView)findViewById(R.id.img)).setImageBitmap(bitmap);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK&&requestCode==REQUEST_CODE){
            if (null!=data){
                String result=data.getStringExtra(CodeUtils.RESULT_STRING);
                Toast.makeText(this, "result:"+result, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
