package alien.com.net;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Alien on 2018-05-01.
 */

public class LoadingDialog extends Dialog {
    private boolean cancelable;
    public LoadingDialog(@NonNull Context context) {
        this(context,false);
    }

   public LoadingDialog(@NonNull Context context,boolean cancelable){
        super(context,R.style.loading_dialog_style);
        this.cancelable=cancelable;
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
        setCanceledOnTouchOutside(this.cancelable);
    }
}
