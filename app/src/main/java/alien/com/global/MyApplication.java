package alien.com.global;

import android.app.Application;

/**
 * Created by Alien on 2018-04-16.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication=null;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static MyApplication getInstance(){
        if (null==myApplication){
            myApplication=new MyApplication();
        }
        return myApplication;
    }
}
