package debug;

import com.gavel.auth.BuildConfig;
import com.gavel.core.CoreApplication;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */

public class AuthApplication extends CoreApplication
{

    @Override
    protected boolean isDebug()
    {
        return BuildConfig.DEBUG;
    }
}
