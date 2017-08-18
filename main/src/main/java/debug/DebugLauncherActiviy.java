package debug;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gavel.core.other.PathConfig;

/**
 * Created by jhhuang on 2017/8/18
 * QQ:781913268
 * Description：xxx
 */
public class DebugLauncherActiviy extends AppCompatActivity
{

    private void action2next()
    {
        ARouter.getInstance().build(PathConfig.MAIN_HOME)
                .navigation();
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        action2next();
    }
}
