package alien.com.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import alien.com.net.R;

public class ScanActivity extends AppCompatActivity {
    private static int REQUEST_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        findViewById(R.id.scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ScanActivity.this, CaptureActivity.class),REQUEST_CODE);
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
